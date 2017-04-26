package com.betamedia.qe.af.core.fwtestrunner.classloader;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Supplier;


/**
 * Created by mbelyaev on 4/10/17.
 */
public interface ContextClassLoaderManagingExecutor {
    void store(MultipartFile jar);

    void setJarPath(String jarPath);

    <T> List<T> run(List<Supplier<T>> suppliers);

    <T> List<T> run(List<Supplier<T>> suppliers, MultipartFile jar);

}
