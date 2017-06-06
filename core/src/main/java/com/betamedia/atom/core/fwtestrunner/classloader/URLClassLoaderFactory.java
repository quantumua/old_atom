package com.betamedia.atom.core.fwtestrunner.classloader;

import java.net.MalformedURLException;

/**
 * Created by mbelyaev on 4/10/17.
 */
public interface URLClassLoaderFactory {
    ClassLoader get(String jarPath, ClassLoader parent) throws MalformedURLException;
}
