package com.betamedia.qe.af.core.fwtestrunner.classloader;

import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.Callable;


/**
 * Created by mbelyaev on 4/10/17.
 */
public interface ContextClassLoaderManagingExecutor {
    void store(MultipartFile jar);

    void setJarPath(String jarPath);

    void run(List<Runnable> runnable);

    void run(List<Runnable> runnable, MultipartFile jar);

}
