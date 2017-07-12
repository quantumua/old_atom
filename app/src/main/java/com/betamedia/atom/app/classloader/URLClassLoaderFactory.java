package com.betamedia.atom.app.classloader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author mbelyaev
 * @since 4/10/17
 */
public interface URLClassLoaderFactory {
    ClassLoader get(URL url, ClassLoader parent);

    URL getURL(String jarPath) throws MalformedURLException;
}
