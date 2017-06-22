package com.betamedia.atom.core.fwtestrunner.classloader;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


/**
 * @author mbelyaev
 * @since 4/10/17
 */
public interface ContextClassLoaderManagingExecutor {
    void store(MultipartFile jar);

    void setJarPath(String jarPath);

    <T> List<T> run(List<Supplier<T>> suppliers, Optional<String> jarPath);

    String getJarVersion();

}
