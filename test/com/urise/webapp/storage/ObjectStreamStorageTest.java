package com.urise.webapp.storage;

/**
 * Created by Andrey on 11.01.2017.
 */
public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new PathStorage(STORAGE_PATH));//super(new FileStorage(STORAGE_FILE));
    }
}