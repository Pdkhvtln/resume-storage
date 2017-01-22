package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 17.01.2017.
 */
public abstract class AbstractMapStorage extends AbstractStorage {
    protected Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
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
    protected void doDelete(Object SearchKey) {
        map.remove(SearchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> result = new ArrayList<Resume>(map.values());
        return result;
    }
}
