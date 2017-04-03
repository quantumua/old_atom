package com.betamedia.qe.af;

import com.betamedia.qe.af.core.holder.AppContextHolder;
import com.betamedia.qe.af.core.business.ClassLoaderInvocationHandler;
import com.betamedia.qe.af.webservice.storage.StorageProperties;
import com.betamedia.qe.af.webservice.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

@SpringBootApplication(scanBasePackages = {"com.betamedia.qe.af"})
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(StorageService storageService,
                             ConfigurableApplicationContext ctx,
                             ClassLoaderInvocationHandler classLoaderInvocationHandler) {
        return args -> {
            storageService.deleteAll();
            storageService.init();
            AppContextHolder.setContext(ctx);
            classLoaderInvocationHandler.setClassLoader(
                    new URLClassLoader(
                            new URL[]{Paths.get("./tests.jar").toUri().toURL()},
                            Thread.currentThread().getContextClassLoader()));
        };
    }

}
