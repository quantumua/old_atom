package com.betamedia.atom.core.configuration;

import com.betamedia.atom.core.configuration.properties.FeedGatewayProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;


/**
 * This post-processor looks for any FeedGatewayProperties defined in the application, selects one and marks it
 * as {@link org.springframework.context.annotation.Primary} to satisfy GS stub configuration.
 * TODO should be removed once GigaSpaces connectors are removed from application
 *
 * @author mbelyaev
 * @since 7/3/17
 */
@Service
public class PrimaryFeedGatewayPropertiesPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = LogManager.getLogger(PrimaryFeedGatewayPropertiesPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
        String[] feedProps = bf.getBeanNamesForType(FeedGatewayProperties.class);
        if (feedProps.length < 1) return;
        bf.getBeanDefinition(feedProps[0]).setPrimary(true);
        logger.info("Set '" + feedProps[0] + "' as primary data source");
    }
}
