package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.configuration.properties.SpaceProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;


/**
 * This post-processor looks for any SpaceProperties defined in the application, selects one and marks it
 * as {@link org.springframework.context.annotation.Primary} to satisfy GS stub configuration.
 * TODO should be removed once GigaSpaces connectors are removed from application
 *
 * @author mbelyaev
 * @since 7/3/17
 */
@Service
public class PrimarySpacePropertiesPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = LogManager.getLogger(PrimarySpacePropertiesPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
        String[] spaceProps = bf.getBeanNamesForType(SpaceProperties.class);
        if (spaceProps.length < 1) return;
        bf.getBeanDefinition(spaceProps[0]).setPrimary(true);
        logger.info("Set '" + spaceProps[0] + "' as primary data source");
    }
}
