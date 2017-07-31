package com.betamedia.atom.core.holders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Exposes application context through static method for use by non-managed components.
 *
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
public class AppContextHolder {

    private static volatile ConfigurableApplicationContext context;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static ConfigurableApplicationContext getContext(){
        return context;
    }
}
