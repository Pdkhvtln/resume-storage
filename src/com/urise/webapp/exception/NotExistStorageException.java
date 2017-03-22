package com.urise.webapp.exception;

/**
 * Created by Andrey on 01.01.2017.
 */
public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
