package com.betamedia.qe.af;

import com.betamedia.qe.af.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.qe.af.core.holders.AppContextHolder;
import com.betamedia.qe.af.core.fwtestrunner.storage.StorageProperties;
import com.betamedia.qe.af.core.fwtestrunner.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.betamedia.qe.af"})
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(StorageService storageService,
                             ConfigurableApplicationContext ctx,
                             ContextClassLoaderManagingExecutor contextClassLoaderManagingExecutor) {
        return args -> {
            storageService.deleteAll();
            storageService.init();
            AppContextHolder.setContext(ctx);
            contextClassLoaderManagingExecutor.setJarPath("testslibrary.jar");
        };
    }

}
