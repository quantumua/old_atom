package com.betamedia.qe.af.core.testlink;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 5/16/17.
 */
@Component
public class TestLinkServiceLocator implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TestLinkServiceLocator.context = applicationContext;
    }

    public static TestLinkService getTestLinkService() {
        return context.getBean(TestLinkService.class);
    }
}
