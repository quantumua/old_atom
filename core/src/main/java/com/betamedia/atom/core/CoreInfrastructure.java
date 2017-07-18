package com.betamedia.atom.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/26/17.
 */
@SpringBootApplication
@PropertySource("classpath:config/core.properties")
public class CoreInfrastructure {
}
