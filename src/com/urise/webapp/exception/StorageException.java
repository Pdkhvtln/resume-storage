package com.urise.webapp.exception;

import java.io.IOException;

/**
 * Created by Andrey on 01.01.2017.
 */
public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message) {
        this(message, null, null);
    }

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public StorageException(String message, Exception e) {
        this(message, null, e);
    }


    public String getUuid() {
        return uuid;
    }


    /*
        public StorageException(String message, String uuid, ClassNotFoundException e) {
        super(message, e);
        this.uuid = uuid;
    }

        public StorageException(String uuid) {
        this.uuid = uuid;
    }*/
}
