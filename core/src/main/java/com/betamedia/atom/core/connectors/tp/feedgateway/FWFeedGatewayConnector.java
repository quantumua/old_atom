package com.betamedia.atom.core.connectors.tp.feedgateway;


import com.betamedia.atom.core.configuration.properties.FeedGatewayProperties;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.connector.FeedGatewayConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public abstract class FWFeedGatewayConnector<T extends EnvironmentDependent> extends FeedGatewayConnector implements EnvironmentDependent {

    private static final Logger logger = LogManager.getLogger(FWFeedGatewayConnector.class);

    @Autowired
    private FeedGatewayProperties<T> feedGatewayProperties;

    @PostConstruct
    public void init() {
        try {
            logger.info("Initializing FeedGatewayConnector {} ...", getEnvironment());
            initProperties();
            super.connect();
            logger.info("FeedGatewayConnector {} is initialized successfully.", getEnvironment());
        } catch (Throwable t) {
            logger.error("Error while creating FeedGatewayConnector {}.", getEnvironment());
            t.printStackTrace();
            throw t;
        }
    }

    @PreDestroy
    public void destroy() {
        super.destroy();
    }

    private void initProperties() {
        this.spaceGroups = feedGatewayProperties.getGroups();
        this.spaceLocators = feedGatewayProperties.getLocators();
        this.spaceUsername = feedGatewayProperties.getSpaceUsername();
        this.spacePassword = feedGatewayProperties.getSpacePassword();
        this.spaceUrl = feedGatewayProperties.getSpaceUrl();
    }
}
