package com.betamedia.qe.af.components;

import lombok.AllArgsConstructor;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
@AllArgsConstructor
public class SUTPropertiesHolderImpl implements SUTPropertiesHolder {
    private Properties properties;

    @Override
    public <T> T get(String key) {
        return (T) properties.get(key);
    }

    @Override
    public void set(Properties properties) {
        this.properties=properties;
    }
}
