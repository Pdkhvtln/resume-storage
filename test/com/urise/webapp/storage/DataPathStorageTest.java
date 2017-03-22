package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.DataStreamSerializer;
import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

/**
 * Created by Andrey on 11.01.2017.
 */
public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_PATH.getAbsolutePath(), new DataStreamSerializer()));
    }
}