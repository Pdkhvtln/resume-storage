package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

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
        if (index >= 0) {//резюме найдено, возвращаем результат
            return storage[index];
        }
        //если резюме с таким uuid нет в БД
        return null;
    }

    protected abstract int getIndex(String uuid);
}
