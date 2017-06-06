package com.betamedia.atom.core.connectors.tp.feedgateway;

/**
 * @author Maksym Tsybulskyy
 * Date: 4/21/17.
 */

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.environment.tp.properties.FeedGatewayPropertiesHolder;
import com.betamedia.tp.api.connector.FeedGatewayConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class FWFeedGatewayConnector<T extends EnvironmentDependent> extends FeedGatewayConnector implements EnvironmentDependent {

    private static final Logger logger = LogManager.getLogger(FWFeedGatewayConnector.class);

    @Autowired
    private FeedGatewayPropertiesHolder<T> feedGatewayPropertiesHolder;

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
        this.spaceGroups = feedGatewayPropertiesHolder.getGWSpaceGroups();
        this.spaceLocators = feedGatewayPropertiesHolder.getGWSpaceLocators();
        this.spaceUsername = feedGatewayPropertiesHolder.getGWSpaceUsername();
        this.spacePassword = feedGatewayPropertiesHolder.getGWSpacePassword();
        this.spaceUrl = feedGatewayPropertiesHolder.getGWSpaceURL();
    }
}
