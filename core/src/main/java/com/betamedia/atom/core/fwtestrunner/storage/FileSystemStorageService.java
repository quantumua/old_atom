package com.betamedia.atom.core.fwtestrunner.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mbelyaev
 * @since 2/28/17
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String storeToTemp(MultipartFile file) {
        return storeToTemp(file, "");
    }

    @Override
    public String storeToTemp(MultipartFile file, String pathString) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + pathString);
            }
            if (!resolve(Paths.get(pathString)).toFile().exists()) {
                Files.createDirectory(resolve(Paths.get(pathString)));
            }
            Path internalPath = resolve(Paths.get(pathString, file.getOriginalFilename()));
            Files.copy(file.getInputStream(), internalPath, StandardCopyOption.REPLACE_EXISTING);
            return internalPath.toString();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + pathString, e);
        }
    }

    @Override
    public String store(File file, String... pathString) {
        try {
            Path path = Paths.get("", pathString);
            if (!path.toFile().exists()) {
                Files.createDirectories(path);
            }
            Path internalPath = Paths.get(path.toString(), file.getName());
            try(InputStream is = new FileInputStream(file)){
                Files.copy(is, internalPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return internalPath.toString();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getName() + " at " + String.join("/", pathString), e);
        }
    }

    @Override
    public List<String> storeToTemp(MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::storeToTemp)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> storeToTemp(MultipartFile[] files, String pathString) {
        return Arrays.stream(files)
                .map(file -> storeToTemp(file, pathString))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String pathString) {
        try {
            Files.delete(Paths.get(pathString));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + pathString, e);
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
    public Stream<Path> loadAll(String... pathString) {
        try {
            return Files.walk(Paths.get("", pathString), 1)
                    .filter(path -> !path.equals(Paths.get("", pathString)));
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
