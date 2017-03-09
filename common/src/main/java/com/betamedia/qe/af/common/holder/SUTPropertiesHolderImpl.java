package com.betamedia.qe.af.common.holder;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
public class SUTPropertiesHolderImpl implements SUTPropertiesHolder {
    private Properties properties;

    public SUTPropertiesHolderImpl(Properties properties) {
        this.properties = properties;
    }

    @Override
    public <T> T get(String key) {
        return (T) properties.get(key);
    }

    @Override
    public void set(Properties properties) {
        this.properties = properties;
    }
}
