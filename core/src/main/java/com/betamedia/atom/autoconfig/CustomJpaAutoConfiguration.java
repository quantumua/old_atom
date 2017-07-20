package com.betamedia.atom.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * Configures Hibernate JPA Transaction management if any datasources have been enabled.
 * Must be processed after DataSource-defining configuration classes to prevent unnecessary
 * {@link javax.persistence.EntityManagerFactory} instantiation.
 * Consider migration to Spring AutoConfiguration for more reliable context initialization.
 *
 * @author mbelyaev
 * @since 7/18/17
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(EnvironmentAutoConfiguration.class)
@Import(HibernateJpaAutoConfiguration.class)
public class CustomJpaAutoConfiguration {
}
