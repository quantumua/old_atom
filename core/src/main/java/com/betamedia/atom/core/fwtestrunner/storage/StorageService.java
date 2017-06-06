package com.betamedia.atom.core.fwtestrunner.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by mbelyaev on 2/28/17.
 */
public interface StorageService {

    void init();

    String store(MultipartFile file);

    String store(MultipartFile file, String filename);

    List<String> store(MultipartFile[] files);

    List<String> store(MultipartFile[] files, String postfix);

    void delete(String path);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
