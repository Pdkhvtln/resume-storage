package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_MAX_LENGTH = 10000;
    protected Resume[] storage = new Resume[STORAGE_MAX_LENGTH];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {//if the resume did not find in storage
            throw new NotExistStorageException(uuid);
        }
        //the resume found
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (isOverflow()) {
            throw new StorageException("Storage overflow",r.getUuid());        }
        if (Resume.isResume(r)) {
            int index = getIndex(r.getUuid());
            if (index < 0) {//The resume with such uuid did not find in Storage, will add it
/*                if (size == 0)
                    storage[size] = r;
                else*/
                    insertResume(r, index);
                size++;
            } else
                throw new ExistStorageException(r.getUuid());
        } else
            System.out.println("Невозможно добавить в хранилище null значение!");
    }

    public void update(Resume r) {
        if (Resume.isResume(r)) {
            int index = getIndex(r.getUuid());
            if (index >= 0) {//the resume found
                storage[index] = r;
            } else
                throw new NotExistStorageException(r.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//the resume found
            eraseResume(index);
            size--;
            storage[size] = null;
        }
        else
            throw new NotExistStorageException(uuid);
    }

    protected boolean isOverflow() {
        return (size == STORAGE_MAX_LENGTH);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void eraseResume(int index);
}
