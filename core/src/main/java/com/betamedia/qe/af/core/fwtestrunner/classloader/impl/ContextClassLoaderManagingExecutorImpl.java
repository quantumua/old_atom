package com.betamedia.qe.af.core.fwtestrunner.classloader.impl;

import com.betamedia.qe.af.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.qe.af.core.fwtestrunner.classloader.URLClassLoaderFactory;
import com.betamedia.qe.af.core.fwtestrunner.storage.StorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 4/10/17.
 */

/**
 * <h1>ContextClassLoaderManagingExecutorImpl</h1>
 * This class provides context URLClassLoader for execution of given set of Runnables.
 * Proper thread safety mechanisms are utilized to support concurrent classloader updates and code execution.
 *
 * @author Maksym Bieliaiev
 * @version 1.0
 * @see java.net.URLClassLoader
 * @see ReentrantReadWriteLock
 * @since 2017-04-13
 */
@Service
public class ContextClassLoaderManagingExecutorImpl implements ContextClassLoaderManagingExecutor {
    private static final Logger logger = LogManager.getLogger(ContextClassLoaderManagingExecutorImpl.class);

    @Autowired
    private StorageService storageService;
    @Autowired
    private TaskExecutor asyncTaskExecutor;
    @Autowired
    private URLClassLoaderFactory classLoaderFactory;

    private volatile String jarPath;
    private volatile String jarVersion;

    private final ReadWriteLock pathLock = new ReentrantReadWriteLock(true);
    private volatile ReadWriteLock jarLock = new ReentrantReadWriteLock(true);

    /**
     * <p>
     * This method is used to store or replace JAR binary to be used for context classloader.
     * The method first acquires the JAR path write lock to update JAR path that is used to construct URLClassLoaders.
     * Then, if JAR file write lock is available, it is acquired by execution thread.
     * Old JAR file and JAR path are replaces with new ones and locks are released.</p>
     * <p>
     * In case of another thread executing code that uses old classloader, JAR file write lock will not be available (see {@link #run(List)}).
     * To avoid waiting for potentially expensive operations to complete, new JAR file is stored with autogenerated name and does not overwrite the old JAR file.
     * A separate thread is assigned to delete the old JAR file when the write lock for it becomes available.
     * The JAR path is updated to point to the new JAR file.
     * New JAR file lock is created to control access to the new file.
     * Finally, JAR path write lock is released.</p>
     *
     * @param jar The uploaded JAR binary
     */
    @Override
    public void store(MultipartFile jar) {
        pathLock.writeLock().lock();
        try {
            if (jarLock.writeLock().tryLock()) {
                try {
                    setJarPath(storageService.store(jar));
                } finally {
                    jarLock.writeLock().unlock();
                }
            } else {
                String oldJarPath = jarPath;
                ReadWriteLock oldJarLock = jarLock;
                asyncTaskExecutor.execute(() -> {
                    oldJarLock.writeLock().lock();
                    try {
                        storageService.delete(oldJarPath);
                    } finally {
                        oldJarLock.writeLock().unlock();
                    }
                });
                setJarPath(storageService.store(jar, "testslibrary-" + UUID.randomUUID() + ".jar"));
                this.jarLock = new ReentrantReadWriteLock(true);
            }
            setJarVersion();
        } finally {
            pathLock.writeLock().unlock();
        }
    }

    /**
     * <p>
     * This method is used to set JAR path that is used to construct URLClassLoaders.
     * Typically used to set default library to use for context classloader after application deployment.
     * </p>
     *
     * @param jarPath The path to JAR file
     */
    @Override
    public void setJarPath(String jarPath) {
        pathLock.writeLock().lock();
        try {
            this.jarPath = jarPath;
            setJarVersion();
        } finally {
            pathLock.writeLock().unlock();
        }
    }

    /**
     * <p>
     * This method executes Runnable objects, providing custom context classloader for them.
     * The method first acquires read lock on JAR path - execution will block until it's available if JAR file is currently being updated.
     * Reference to current JAR file lock is stored in local variable, as it is expected to change once classloader
     * for current JAR path is initialized and JAR path read lock is released.
     * </p>
     * <p>
     * After acquiring JAR file read lock, we get the classloader for the JAR path. Then, JAR path read lock is released
     * because we no longer need the path variable itself. JAR file read lock is held for entire duration of Runnables' execution.
     * This allows other threads to update JAR file without waiting for Runnables that use old JAR file to complete (see {@link #store(MultipartFile)}).
     * </p>
     * <p>
     * Runnables are executed in a newly created thread pool to ensure proper thread context classloader propagation.
     * After finishing execution, JAR file read lock is released and original context classloader for thread is restored.
     * </p>
     *
     * @param suppliers List of Supplier objects to execute
     */
    @Override
    public <T> List<T> run(List<Supplier<T>> suppliers) {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader;
        pathLock.readLock().lock();
        try {
            ReadWriteLock jarLock = this.jarLock;
            jarLock.readLock().lock();
            try {
                try {
                    classLoader = classLoaderFactory.get(jarPath, parent);
                } catch (MalformedURLException e) {
                    logger.error("", e);
                    throw new RuntimeException(e);
                } finally {
                    pathLock.readLock().unlock();
                }
                Thread.currentThread().setContextClassLoader(classLoader);
                return executeInPool(suppliers);
            } finally {
                jarLock.readLock().unlock();
            }
        } finally {
            Thread.currentThread().setContextClassLoader(parent);
        }
    }


    /**
     * <p>
     * This method executes Runnable objects, providing temporary context classloader made using uploaded JAR file.
     * JAR file is stored with autogenerated name and is deleted as soon as the execution of Runnables is completed.
     * This allows users to temporarily override configured JAR library with their own without interfering with other threads.
     * </p>
     *
     * @param suppliers List of Supplier objects to execute
     * @param jar       The uploaded JAR binary
     */
    @Override
    public <T> List<T> run(List<Supplier<T>> suppliers, MultipartFile jar) {
        String jarPath = storageService.store(jar, "temp-" + UUID.randomUUID() + ".jar");
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader classLoader = classLoaderFactory.get(jarPath, parent);
            Thread.currentThread().setContextClassLoader(classLoader);
            return executeInPool(suppliers);
        } catch (MalformedURLException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(parent);
            storageService.delete(jarPath);
        }
    }

    private <T> List<T> executeInPool(List<Supplier<T>> suppliers) {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<CompletableFuture<T>> futures = suppliers.stream()
                .map(s -> CompletableFuture.supplyAsync(s, pool))
                .collect(Collectors.toList());
        pool.shutdown();
        return all(futures).join();
    }

    private <T> CompletableFuture<List<T>> all(List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );
    }

    private void setJarVersion() {
        pathLock.writeLock().lock();
        try (JarFile jar = new JarFile(jarPath)) {
            Manifest manifest = jar.getManifest();
            Attributes attributes = manifest.getMainAttributes();
            jarVersion = attributes.getValue("Implementation-Version");
        } catch (Exception ex) {
            logger.warn("Couldn't get testslibrary version", ex.getMessage());
        } finally {
            pathLock.writeLock().unlock();
        }
    }

    public String getJarVersion() {
        pathLock.readLock().lock();
        try {
            return jarVersion;
        } finally {
            pathLock.readLock().unlock();
        }

    }
}
