package com.urise.webapp.storage;

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
        if (index >= 0) {//the resume found
            return storage[index];
        }
        //if the resume did not find in storage
        return null;
    }

    protected boolean isOverflow() {
        return (size == STORAGE_MAX_LENGTH);
    }

    protected abstract int getIndex(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public boolean update(Resume r) {
        if (Resume.isResume(r)) {
            int index = getIndex(r.getUuid());
            if (index >= 0) {//the resume found
                storage[index] = r;
                return true;//success
            }
        }
       return false;//failure
    }

    public boolean delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//the resume found
            System.arraycopy(storage, index + 1, storage, index, --size - index);
            return true;//success
        }
        return false;//failure
    }
}
