package com.betamedia.qe.af.common.connectors.feedgateway;

import com.betamedia.tp.api.connector.FeedGatewayConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/20/17.
 */
@Component
public class AFFeedGatewayConnector extends FeedGatewayConnector {

    private static final Logger logger = LogManager.getLogger(AFFeedGatewayConnector.class);

    @Autowired
    private AFFeedGatewayProperties afFeedGatewayProperties;

    @PostConstruct
    public void init() {
        try {
            logger.info("Initializing AFFeedGatewayConnector ...");
            initProperties();
            super.connect();
            logger.info("AFFeedGatewayConnector is initialized successfully.");
        } catch (Throwable t) {
            logger.error("Error while creating AFFeedGatewayConnector.");
            t.printStackTrace();
            throw t;
        }
    }

    @PreDestroy
    public void destroy() {
        super.destroy();
    }

    private void initProperties() {
        this.spaceGroups = afFeedGatewayProperties.getAfSpaceGroups();
        this.spaceLocators = afFeedGatewayProperties.getAfSpaceLocators();
        this.spaceUsername = afFeedGatewayProperties.getAfSpaceUsername();
        this.spacePassword = afFeedGatewayProperties.getAfSpacePassword();
        this.spaceUrl = afFeedGatewayProperties.getAfSpaceURL();
    }
}
