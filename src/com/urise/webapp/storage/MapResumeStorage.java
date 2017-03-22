package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 06.01.2017.
 */
public class MapResumeStorage extends AbstractStorage<Resume> {
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
    protected void doSave(Resume r, Resume SearchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume r, Resume SearchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume SearchKey) {
        map.remove(SearchKey.getUuid());
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> result = new ArrayList<Resume>(map.values());
        return result;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }



}
