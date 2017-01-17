package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Created by Andrey on 06.01.2017.
 */
public class MapUuidStorage extends AbstractMapStorage {
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<Resume>(map.values());
        result.sort(UUID_COMPARATOR);
        return result;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.replace(r.getUuid(), r);
    }

    @Override
    protected String getSearchKey(String uuid) {
        if (map.containsKey(uuid))
            return uuid;
        return null;
    }
}
