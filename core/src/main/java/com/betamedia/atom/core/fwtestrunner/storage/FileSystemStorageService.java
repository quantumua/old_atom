package com.betamedia.atom.core.fwtestrunner.storage;

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
 * @since 7/17/17
 */
public final class FileSystemStorageService {
    public static String store(File file, String... pathString) {
        try {
            Path destination = preparePath(file.getName(), "", pathString);
            try (InputStream is = new FileInputStream(file)) {
                Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            return destination.toString();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getName() + " at " + String.join("/", pathString), e);
        }
    }

    public static String storeSystemResource(String resource, String filename, String... pathString) {
        try (InputStream is = ClassLoader.getSystemResourceAsStream(resource)) {
            return FileSystemStorageService.store(is, filename, pathString);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename + " at " + String.join("/", pathString), e);
        }
    }

    public static String store(InputStream is, String filename, String... pathString) {
        try {
            Path destination = preparePath(filename, "", pathString);
            Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toString();
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename + " at " + String.join("/", pathString), e);
        }
    }

    public static Path preparePath(String filename, String first, String... more) {
        Path directory = Paths.get(first, more);
        try {
            if (!directory.toFile().exists()) {
                Files.createDirectories(directory);
            }
            return directory.resolve(filename);
        } catch (IOException e) {
            throw new StorageException("Failed to prepare path at " + first + "/" + String.join("/", more), e);
        }
    }

    public static Stream<Path> loadAll(String... pathString) {
        try {
            return Files.walk(Paths.get("", pathString), 1)
                    .filter(path -> !path.equals(Paths.get("", pathString)));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    private FileSystemStorageService() {
    }

}
