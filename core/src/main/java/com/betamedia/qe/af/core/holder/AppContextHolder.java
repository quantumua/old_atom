package com.betamedia.qe.af.core.holder;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public class AppContextHolder {

    private static ConfigurableApplicationContext context;


    public static void setContext(ConfigurableApplicationContext context) {
        AppContextHolder.context = context;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static ConfigurableApplicationContext getContext(){
        return context;
    };
}
