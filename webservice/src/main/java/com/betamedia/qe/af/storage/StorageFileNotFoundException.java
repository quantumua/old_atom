package com.betamedia.qe.af.storage;

/**
 * Created by mbelyaev on 2/28/17.
 */
public class StorageFileNotFoundException extends StorageException{

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
