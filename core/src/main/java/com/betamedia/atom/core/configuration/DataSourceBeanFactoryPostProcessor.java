package com.betamedia.atom.core.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author mbelyaev
 * @since 7/3/17
 */
@Service
public class DataSourceBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = LogManager.getLogger(DataSourceBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
        String[] dataSources = bf.getBeanNamesForType(DataSource.class);
        if (dataSources.length < 1) return;
        logger.info("Selected " + dataSources[0] + " as primary data source");
        BeanDefinition primaryDataSource = bf.getBeanDefinition(dataSources[0]);
        primaryDataSource.setPrimary(true);
        String primaryPrefix = dataSources[0].split("(?=\\p{Upper})")[0];
        setPrimary(DataSourceProperties.class, primaryPrefix, bf);
        setPrimary(EntityManagerFactory.class, primaryPrefix, bf);
        setPrimary(LocalContainerEntityManagerFactoryBean.class, primaryPrefix, bf);
        logger.info("Set " + dataSources[0] + " as primary data source");
    }

    private static void setPrimary(Class<?> type, String prefix, ConfigurableListableBeanFactory beanFactory) {
        String[] beanNames = beanFactory.getBeanNamesForType(type);
        Arrays.stream(beanNames)
                .filter(name -> name.startsWith(prefix))
                .map(beanFactory::getBeanDefinition)
                .forEach(beanDefinitionFactory -> beanDefinitionFactory.setPrimary(true));
    }
}
