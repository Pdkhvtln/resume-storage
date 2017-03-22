package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;
import com.urise.webapp.storage.serializer.XmlStreamSerializer;

/**
 * Created by Andrey on 11.01.2017.
 */
public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_PATH.getAbsolutePath(), new XmlStreamSerializer()));
    }
}