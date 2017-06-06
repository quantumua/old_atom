package com.betamedia.atom.core.fwtestrunner.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.exists;

/**
 * Created by mbelyaev on 2/28/17.
 */
@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String store(MultipartFile file) {
        return store(file, "");
    }

    @Override
    public String store(MultipartFile file, String subDirectory) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + subDirectory);
            }
            if (!resolve(Paths.get(subDirectory)).toFile().exists()){
                Files.createDirectory(resolve(Paths.get(subDirectory)));
            }
            Path internalPath = resolve(Paths.get(subDirectory, file.getOriginalFilename()));
            Files.copy(file.getInputStream(), internalPath, StandardCopyOption.REPLACE_EXISTING);
            return internalPath.toString();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + subDirectory, e);
        }
    }

    @Override
    public List<String> store(MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::store)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> store(MultipartFile[] files, String subDirectory) {
        return Arrays.stream(files)
                .map(file -> store(file, subDirectory))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + path, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    private Path resolve(Path relativePath) {
        return this.rootLocation.resolve(relativePath);
    }
}
