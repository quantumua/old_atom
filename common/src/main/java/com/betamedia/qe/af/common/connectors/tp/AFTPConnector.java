package com.betamedia.qe.af.common.connectors.tp;

import com.betamedia.tp.api.connector.ClientSessionIdHandler;
import com.betamedia.tp.api.connector.ClientTPConnector;
import com.betamedia.tp.api.model.TPSession;
import com.betamedia.tp.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/17/17.
 */
@Component
public class AFTPConnector extends ClientTPConnector {

    @Autowired
    private AFSpaceProperties afSpaceProperties;

    private ClientSessionIdHandler clientSessionIdHandler;
    private IUserService userService;

    @PostConstruct
    public void init() {
        try {
            log.info("Initializing AFTPConnector ...");
            initProperties();
            super.connect();
            log.info("AFTPConnector is initialized successfully.");
        } catch (Throwable t) {
            log.error("Error while creating AFTPConnector.");
            t.printStackTrace();
            throw t;
        }
    }

    @PreDestroy
    public void destroy() {
        super.destroy();
    }

    private void initProperties() {
        this.spaceGroups = afSpaceProperties.getAfSpaceGroups();
        this.spaceLocators = afSpaceProperties.getAfSpaceLocators();
        this.spaceUsername = afSpaceProperties.getAfSpaceUsername();
        this.spacePassword = afSpaceProperties.getAfSpacePassword();
        this.spaceUrl = afSpaceProperties.getAfSpaceURL();
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
        TPSession session = userService.login(afSpaceProperties.getAfUsername(), afSpaceProperties.getAfPwd());
        clientSessionIdHandler = new ClientSessionIdHandler();
        this.getMetaArgumentsHandler().setSessionId(session.getId());
    }
}
