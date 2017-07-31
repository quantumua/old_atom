package com.betamedia.atom.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/26/17.
 */
@SpringBootApplication
@EnableConfigurationProperties(JpaProperties.class)
@PropertySource("classpath:config/core.properties")
public class CoreInfrastructure {
}
