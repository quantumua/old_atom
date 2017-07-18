package com.betamedia.atom.app.storage;

import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import com.betamedia.atom.core.fwtestrunner.storage.StorageException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author mbelyaev
 * @since 2/28/17
 */
@Service
public class FileSystemTempStorageService implements TempStorageService {
    private static final Logger logger = LogManager.getLogger(FileSystemTempStorageService.class);

    private final String rootLocation;

    @Autowired
    public FileSystemTempStorageService(StorageProperties properties) {
        this.rootLocation = properties.getLocation();
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(Paths.get(rootLocation));
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String storeToTemp(MultipartFile file, String... pathString) {
        try {
            Path destination = FileSystemStorageService.preparePath(file.getOriginalFilename(), rootLocation, pathString);
            file.transferTo(destination.toAbsolutePath().toFile());
            return destination.toString();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getName() + " at " + String.join("/", pathString), e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(rootLocation).toFile());
    }

}
