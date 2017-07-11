package com.betamedia.atom.core.fwtestrunner.storage;

import com.betamedia.atom.core.configuration.properties.StorageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * @author mbelyaev
 * @since 2/28/17
 */
@Service
public class FileSystemStorageService implements StorageService {
    private static final Logger logger = LogManager.getLogger(FileSystemStorageService.class);

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
            if (file == null || file.isEmpty()) {
                logger.debug("Trying to store empty file " + pathString);
                return null;
            }
            if (!resolve(Paths.get(pathString)).toFile().exists()) {
                Files.createDirectory(resolve(Paths.get(pathString)));
            }
            Path internalPath = resolve(Paths.get(pathString, file.getOriginalFilename().replaceAll("[^.a-zA-Z0-9]", "_")));
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
    public void delete(String pathString) {
        try {
            Files.delete(Paths.get(pathString));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + pathString, e);
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
