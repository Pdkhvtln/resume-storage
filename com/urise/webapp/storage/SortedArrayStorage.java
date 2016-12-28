package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Created by Andrey on 28.12.2016.
 */

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage,0,size,new Resume(uuid));
    }
}
