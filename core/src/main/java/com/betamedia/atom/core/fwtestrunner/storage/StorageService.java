package com.betamedia.atom.core.fwtestrunner.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author mbelyaev
 * @since 2/28/17
 */
public interface StorageService {

    void init();

    String storeToTemp(MultipartFile file);

    String storeToTemp(MultipartFile file, String pathString);

    String store(File file, String... pathString);

    List<String> storeToTemp(MultipartFile[] files);

    List<String> storeToTemp(MultipartFile[] files, String pathString);

    void delete(String pathString);

    Stream<Path> loadAll();

    Stream<Path> loadAll(String... pathString);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
