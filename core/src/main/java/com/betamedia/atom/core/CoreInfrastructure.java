package com.betamedia.atom.core;

import com.betamedia.atom.core.fwtestrunner.storage.StorageProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/26/17.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@PropertySource("classpath:core.properties")
public class CoreInfrastructure {

}
