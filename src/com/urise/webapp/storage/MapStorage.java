package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Andrey on 06.01.2017.
 */
public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map;

    public MapStorage() {
        map = new HashMap<String, Resume>();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void save(Resume r) {
        if (map.containsKey(r.getUuid()))
            throw new ExistStorageException(r.getUuid());
        else
            map.put(r.getUuid(), r);
    }

    @Override
    public Resume get(String uuid) {
        if (map.containsKey(uuid))
            return map.get(uuid);
        else
            throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (map.containsKey(uuid))
            map.remove(uuid);
        else
            throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] r = new Resume[map.size()];
        int i = 0;
        for (Map.Entry<String, Resume> m : map.entrySet()) {
            r[i] = m.getValue();
            i++;
        }
        return r;
    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    public void update(Resume r) {
        if (map.containsKey(r.getUuid()))
            map.replace(r.getUuid(), r);
        else
            throw new NotExistStorageException(r.getUuid());
    }
}
