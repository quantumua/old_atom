package com.betamedia.atom.core.fwtestrunner.storage;

/**
 * Created by mbelyaev on 2/28/17.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
