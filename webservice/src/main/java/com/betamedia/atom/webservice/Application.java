package com.betamedia.atom.webservice;

import com.betamedia.atom.app.AppInfrastructure;
import com.betamedia.atom.app.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.app.storage.TempStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppInfrastructure.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(TempStorageService storageService,
                             ContextClassLoaderManagingExecutor contextClassLoaderManagingExecutor) {
        return args -> {
            storageService.deleteAll();
            storageService.init();
            contextClassLoaderManagingExecutor.setJarPath("testslibrary.jar");
        };
    }

}
