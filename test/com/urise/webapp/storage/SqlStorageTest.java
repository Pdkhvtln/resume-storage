package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.DataStreamSerializer;
import com.urise.webapp.utils.Config;

/**
 * Created by Andrey on 11.01.2017.
 */
public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}