package com.urise.webapp.exception;

/**
 * Created by Andrey on 01.01.2017.
 */
public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }


    public StorageException(String uuid) {
        this.uuid = uuid;
    }
}
