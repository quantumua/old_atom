package com.betamedia.qe.af;

import com.betamedia.qe.af.common.holder.AppContextHolder;
import com.betamedia.qe.af.webservice.storage.StorageProperties;
import com.betamedia.qe.af.webservice.storage.StorageService;
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
//        args = Arrays.asList("--spring.config.location=driver.properties", "tests.xml").toArray(args);
        SpringApplication.run(Application.class, args);
//        RunTestHandler runTestHandler = context.getBean(RunTestHandler.class);
//        runTestHandler.handle();
    }

    @Bean
    CommandLineRunner runner(StorageService storageService, ConfigurableApplicationContext ctx) {
        return args -> {
            storageService.deleteAll();
            storageService.init();
            AppContextHolder.setContext(ctx);
        };
    }

}
