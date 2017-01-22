package com.urise.webapp.storage;

/**
 * Created by Andrey on 06.01.2017.
 */
public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected String getSearchKey(String uuid) {
        if (map.containsKey(uuid))
            return uuid;
        return null;
    }
}
