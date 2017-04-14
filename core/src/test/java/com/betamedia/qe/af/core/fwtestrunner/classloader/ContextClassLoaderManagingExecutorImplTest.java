package com.betamedia.qe.af.core.fwtestrunner.classloader;

import com.betamedia.qe.af.core.fwtestrunner.classloader.impl.ContextClassLoaderManagingExecutorImpl;
import com.betamedia.qe.af.core.fwtestrunner.storage.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mbelyaev on 4/10/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContextClassLoaderManagingExecutorImplTest {
    @InjectMocks
    private ContextClassLoaderManagingExecutorImpl executionHandler;
    @Mock
    private StorageService storageService;
    @Mock
    private URLClassLoaderFactory classLoaderFactory;
    @Mock
    private MultipartFile mockFile;
    @Mock
    private ClassLoader mockClassLoader;
    @Mock
    private MultipartFile alteredFile;
    @Mock
    private ClassLoader alteredClassLoader;

    @Test
    public void concurrentExecutionTest() throws Exception {
        String mockPath = "mockPath";
        String alteredPath = "alteredPath";
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        when(storageService.store(mockFile)).thenReturn(mockPath);
        when(storageService.store(eq(mockFile), anyString())).thenReturn(mockPath);
        when(storageService.store(alteredFile)).thenReturn(alteredPath);
        when(storageService.store(eq(alteredFile), anyString())).thenReturn(alteredPath);
        when(classLoaderFactory.get(mockPath, parent)).thenReturn(mockClassLoader);
        when(classLoaderFactory.get(alteredPath, parent)).thenReturn(alteredClassLoader);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Semaphore semaphore1 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(0);
        Arrays.asList(
                (Runnable) () -> {
                    executionHandler.store(mockFile);
                    executionHandler.run(Collections.singletonList(() -> {
                        semaphore1.release();
                        try {
                            semaphore2.acquire();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        mockExecution(mockClassLoader, "MOCK 1");
                    }));
                },
                () -> {
                    try {
                        semaphore1.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    executionHandler.store(alteredFile);
                    semaphore2.release();
                    executionHandler.run(Collections.singletonList(() -> mockExecution(alteredClassLoader, "ALT 1")));
                }
        ).forEach(pool::execute);
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        verify(storageService).delete(mockPath);
    }

    private void mockExecution(ClassLoader expectedClassLoader, String id) {
        System.out.println("STARTED EXECUTING\t" + id);
        System.out.println("CLASSLOADER MATCH\t" + id + ":" + Objects.equals(Thread.currentThread().getContextClassLoader(), expectedClassLoader));
        assertThat(Thread.currentThread().getContextClassLoader(), is(expectedClassLoader));
        System.out.println("FINISHED EXECUTING\t" + id);
    }

}