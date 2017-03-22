package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 06.01.2017.
 */
public class MapUuidStorage extends AbstractStorage<String> {
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
    protected void doSave(Resume r, String SearchKey) {
        map.put(SearchKey, r);
    }

    @Override
    protected void doUpdate(Resume r, String SearchKey) {
        map.put(SearchKey, r);
    }

    @Override
    protected void doDelete(String SearchKey) {
        map.remove(SearchKey);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> result = new ArrayList<>(map.values());
        return result;
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
