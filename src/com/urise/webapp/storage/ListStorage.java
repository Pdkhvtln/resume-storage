package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Andrey on 06.01.2017.
 */
public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();
    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        list.set((Integer) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid))
                return i;
        }
        return null;
    }

    /*@Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }
*/
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>(list);
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    @Override
    public int size() {
        return list.size();
    }

}

