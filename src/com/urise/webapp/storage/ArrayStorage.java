package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public List<Resume> getAllSorted()
    {
        List<Resume> list = new ArrayList<Resume>(Arrays.asList(Arrays.copyOf(storage,size)));
        list.sort(RESUME_COMPARATOR);
        return list;
    }
    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void eraseResume(int index) {
        storage[index] = storage[size - 1];
    }
}
