package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private final int STORAGE_MAX_LENGTH = 10000;
    private Resume[] storage = new Resume[STORAGE_MAX_LENGTH];
    private int size = 0;

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    public boolean save(Resume r) {
        if (isOverflow()) {
            System.out.println("Извините места в хранилище больше нет!");
            return false;//failure
        }
        if (Resume.isResume(r)) {
            int index = getIndex(r.getUuid());
            if (index < 0) {
                storage[size] = r;
                size++;
                return true;//success
            } else
                System.out.println("Резюме с " + r + " уже есть!");
        } else
            System.out.println("Невозможно добавить в хранилище null значение!");
        return false;//failure
    }
}
