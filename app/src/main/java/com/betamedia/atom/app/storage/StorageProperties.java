package com.betamedia.atom.app.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mbelyaev
 * @since 2/28/17
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
