package com.betamedia.atom.app.classloader;

import com.betamedia.atom.app.classloader.impl.ContextClassLoaderManagingExecutorImpl;
import com.betamedia.atom.app.storage.TempStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author mbelyaev
 * @since 4/10/17
 */
@RunWith(MockitoJUnitRunner.class)
public class ContextClassLoaderManagingExecutorImplTest {
    @InjectMocks
    private ContextClassLoaderManagingExecutorImpl executionHandler;
    @Mock
    private TempStorageService storageService;
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
        URL mockURL = new URL("http://mock.org");
        URL alteredURL = new URL("http://alt.org");
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        when(storageService.storeToTemp(mockFile)).thenReturn(mockPath);
        when(storageService.storeToTemp(eq(mockFile), anyString())).thenReturn(mockPath);
        when(storageService.storeToTemp(alteredFile)).thenReturn(alteredPath);
        when(storageService.storeToTemp(eq(alteredFile), anyString())).thenReturn(alteredPath);
        when(classLoaderFactory.getURL(mockPath)).thenReturn(mockURL);
        when(classLoaderFactory.getURL(alteredPath)).thenReturn(alteredURL);
        when(classLoaderFactory.get(mockURL, parent)).thenReturn(mockClassLoader);
        when(classLoaderFactory.get(alteredURL, parent)).thenReturn(alteredClassLoader);
        ExecutorService pool = Executors.newCachedThreadPool();
        Semaphore startedFirstExecutionWithFirstJar = new Semaphore(0);
        Semaphore completedWritingSecondJar = new Semaphore(0);
        Semaphore allowThirdExecutionForSecondJar = new Semaphore(0);
        Arrays.asList(
                (Runnable) () -> {
                    executionHandler.store(mockFile);
                    executionHandler.run(() -> {
                                startedFirstExecutionWithFirstJar.release();
                                try {
                                    completedWritingSecondJar.acquire();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                mockExecution(mockClassLoader, "MOCK 1");
                                return null;
                            },
                            Optional.empty());
                },
                () -> {
                    try {
                        startedFirstExecutionWithFirstJar.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    executionHandler.store(alteredFile);
                    completedWritingSecondJar.release();
                    allowThirdExecutionForSecondJar.release();
                    executionHandler.run(() -> {
                                mockExecution(alteredClassLoader, "ALT 1");
                                return null;
                            },
                            Optional.empty());
                },
                () -> {
                    try {
                        allowThirdExecutionForSecondJar.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    executionHandler.run(() -> {
                                mockExecution(alteredClassLoader, "ALT 2");
                                return null;
                            },
                            Optional.empty());
                }
        ).forEach(pool::execute);
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
    }

    private void mockExecution(ClassLoader expectedClassLoader, String id) {
        System.out.println("STARTED EXECUTING\t" + id);
        System.out.println("CLASSLOADER MATCH\t" + id + ":" + Objects.equals(Thread.currentThread().getContextClassLoader(), expectedClassLoader));
        assertThat(Thread.currentThread().getContextClassLoader(), is(expectedClassLoader));
        System.out.println("FINISHED EXECUTING\t" + id);
    }

}