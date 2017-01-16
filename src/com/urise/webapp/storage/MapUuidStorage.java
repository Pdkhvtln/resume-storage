package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Created by Andrey on 06.01.2017.
 */
public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();
    @Override
    public void clear() {
        map.clear();
    }
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<Resume>(map.values());
        result.sort(RESUME_COMPARATOR);
        return result;
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
        return searchKey != null;
    }

    @Override
    protected String getSearchKey(String uuid) {
        if (map.containsKey(uuid))
            return uuid;
        return null;
    }
}
