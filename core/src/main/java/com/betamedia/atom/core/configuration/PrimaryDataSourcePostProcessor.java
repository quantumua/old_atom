package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;


/**
 * This post-processor looks for any beans that extend EnvironmentDependent and selects the first match to determine the
 * primary environment for application. All bean definitions with prefix matching selected environment will be marked as
 * {@link org.springframework.context.annotation.Primary} to satisfy JPA transaction manager and stub configuration.
 *
 * @author mbelyaev
 * @since 7/3/17
 */
@Service
public class PrimaryDataSourcePostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = LogManager.getLogger(PrimaryDataSourcePostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
        String[] dataSources = bf.getBeanNamesForType(DataSource.class);
        if (dataSources.length < 1) return;
        bf.getBeanDefinition(dataSources[0]).setPrimary(true);
        logger.info("Set '" + dataSources[0] + "' as primary data source");
        Arrays.stream(EnvironmentType.values())
                .map(EnvironmentType::getValue)
                .filter(dataSources[0]::startsWith)
                .flatMap(env -> Arrays.stream(bf.getBeanNamesForType(EntityManagerFactory.class))
                        .filter(emf -> emf.startsWith(env))
                        .map(bf::getBeanDefinition)
                ).forEach(bdf -> bdf.setPrimary(true));
    }
}
