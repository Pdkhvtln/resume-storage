package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private final int STORAGE_MAX_LENGTH = 10000;
    private Resume[] storage = new Resume[STORAGE_MAX_LENGTH];
    private int size = 0;

    private int getIndex(String uuid) {
        if (uuid != null)
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid()))
                    return i;
            }
        return -1;
    }

    private boolean isOverflow() {
        return (size == STORAGE_MAX_LENGTH);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (isOverflow())
            System.out.println("Извините места в хранилище больше нет!");
        else if (Resume.isResume(r)) {
            int index = getIndex(r.getUuid());
            if (index < 0) {
                storage[size] = r;
                size++;
            } else
                System.out.println("Резюме с " + r + " уже есть!");
        } else
            System.out.println("Невозможно добавить в хранилище null значение!");
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//резюме найдено, возвращаем результат
            return storage[index];
        }
        //если резюме с таким uuid нет в БД
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//резюме найдено, возвращаем результат
            System.arraycopy(storage, index + 1, storage, index, --size - index);
            System.out.println("Удаляемое резюме с uuid = " + uuid + " удалено из хранилища.");
        } else
            System.out.println("Удаляемое резюме с uuid = " + uuid + " не было обнаружено в хранилище.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume r) {
        if (Resume.isResume(r)) {
            int index = getIndex(r.getUuid());
            if (index < 0) {
                storage[index] = r;
            } else
                System.out.println("Изменяемого рюзюме с uuid = " + r + " нет в хранилище.");
        } else
            System.out.println("Невозможно изменить резюме которого не существует(null)!");
    }
}
