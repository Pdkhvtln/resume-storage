package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {//Storage {
    protected static final int STORAGE_MAX_LENGTH = 10000;
    protected Resume[] storage = new Resume[STORAGE_MAX_LENGTH];
    protected int size = 0;

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        if (isOverflow()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, (Integer) index);
            size++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        eraseResume((Integer) index);
        size--;
        storage[size] = null;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public int size() {
        return size;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<Resume>(Arrays.asList(Arrays.copyOf(storage, size)));
        return list;
    }

    public void clear() {
        if (size > 0)
            Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    protected boolean isOverflow() {
        return (size == STORAGE_MAX_LENGTH);
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void eraseResume(int index);
}
