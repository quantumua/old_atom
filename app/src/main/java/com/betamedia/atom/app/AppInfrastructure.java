package com.betamedia.atom.app;

import com.betamedia.atom.app.storage.StorageProperties;
import com.betamedia.atom.core.CoreInfrastructure;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Maksym Tsybulskyy
 *         Date: 7/12/2017.
 */
@SpringBootApplication
@Import(CoreInfrastructure.class)
@EnableConfigurationProperties(StorageProperties.class)
@PropertySource("classpath:config/app.properties")
public class AppInfrastructure {
}
