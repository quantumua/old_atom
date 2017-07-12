package com.betamedia.atom.app.classloader.impl;

import com.betamedia.atom.app.classloader.URLClassLoaderFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

/**
 * @author mbelyaev
 * @since 4/10/17
 */
@Service
public class URLClassLoaderFactoryImpl implements URLClassLoaderFactory {
    @Override
    public ClassLoader get(URL url, ClassLoader parent) {
        return new URLClassLoader(new URL[]{url}, parent);
    }

    @Override
    public URL getURL(String jarPath) throws MalformedURLException {
        return Paths.get(jarPath).toUri().toURL();
    }
}
