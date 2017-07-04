package com.betamedia.atom.core.connectors.tp;

import com.betamedia.atom.core.configuration.properties.SpaceProperties;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.connector.ClientSessionIdHandler;
import com.betamedia.tp.api.connector.ClientTPConnector;
import com.betamedia.tp.api.model.TPSession;
import com.betamedia.tp.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/21/17.
 */
public abstract class FWTPConnector<T extends EnvironmentDependent> extends ClientTPConnector implements EnvironmentDependent {

    @Autowired
    private SpaceProperties<T> spaceProperties;


    private ClientSessionIdHandler clientSessionIdHandler;
    private IUserService userService;

    @PostConstruct
    public void init() {
        try {
            log.info("Initializing FWTPConnector {}...", getEnvironment());
            initProperties();
            super.connect();
            log.info("FWTPConnector {} is initialized successfully.", getEnvironment());
        } catch (Throwable t) {
            log.error("Error while creating FWTPConnector {}.", getEnvironment());
            t.printStackTrace();
            throw t;
        }
    }

    @PreDestroy
    public void destroy() {
        super.destroy();
    }

    private void initProperties() {
        this.spaceGroups = spaceProperties.getGroups();
        this.spaceLocators = spaceProperties.getLocators();
        this.spaceUsername = spaceProperties.getSpaceUsername();
        this.spacePassword = spaceProperties.getSpacePassword();
        this.spaceUrl = spaceProperties.getSpaceUrl();
    }

    @Override
    protected void preConnect() {
    }

    @Override
    public ClientSessionIdHandler getMetaArgumentsHandler() {
        return this.clientSessionIdHandler;
    }

    @Override
    protected void postConnect() {
        userService = serviceProxy(IUserService.class);
        TPSession session = userService.login(spaceProperties.getUsername(), spaceProperties.getPassword());
        clientSessionIdHandler = new ClientSessionIdHandler();
        this.getMetaArgumentsHandler().setSessionId(session.getId());
    }

}
