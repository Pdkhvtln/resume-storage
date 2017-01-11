package com.urise.webapp.storage;

/**
 * Created by Andrey on 12.01.2017.
 */
public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapUuidStorage());
    }
}