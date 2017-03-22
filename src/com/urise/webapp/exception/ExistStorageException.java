package com.urise.webapp.exception;

/**
 * Created by Andrey on 01.01.2017.
 */
public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}
