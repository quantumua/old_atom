package com.betamedia.atom.app.classloader;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Supplier;


/**
 * @author mbelyaev
 * @since 4/10/17
 */
public interface ContextClassLoaderManagingExecutor {
    void store(MultipartFile jar);

    void setJarPath(String jarPath);

    <T> T run(Supplier<T> supplier, Optional<String> jarPath);

    String getJarVersion();

}
