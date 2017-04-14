package com.betamedia.qe.af.core.fwtestrunner.classloader.impl;

import com.betamedia.qe.af.core.fwtestrunner.classloader.URLClassLoaderFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

/**
 * Created by mbelyaev on 4/10/17.
 */
@Service
public class URLClassLoaderFactoryImpl implements URLClassLoaderFactory {
    @Override
    public ClassLoader get(String jarPath, ClassLoader parent) throws MalformedURLException {
        return new URLClassLoader(new URL[]{Paths.get(jarPath).toUri().toURL()}, parent);
    }
}
