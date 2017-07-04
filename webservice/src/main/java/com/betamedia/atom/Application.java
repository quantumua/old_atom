package com.betamedia.atom;

import com.betamedia.atom.core.CoreInfrastructure;
import com.betamedia.atom.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CoreInfrastructure.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(StorageService storageService,
                             ContextClassLoaderManagingExecutor contextClassLoaderManagingExecutor) {
        return args -> {
            storageService.deleteAll();
            storageService.init();
            contextClassLoaderManagingExecutor.setJarPath("testslibrary.jar");
        };
    }

}
