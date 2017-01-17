package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 17.01.2017.
 */
public class MapFullNameStorage extends AbstractMapStorage {
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<Resume>(map.values());
        result.sort(FULL_NAME_COMPARATOR);
        return result;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getFullName(), r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.replace(r.getFullName(), r);
    }

    @Override
    protected String getSearchKey(String fullName) {
        if (map.containsKey(fullName))
            return fullName;
        return null;
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getFullName());
        doUpdate(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getFullName());
        doSave(r, searchKey);
    }
}
