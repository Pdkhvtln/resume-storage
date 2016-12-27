package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int storageMaxLenth = 10000;
    private Resume[] storage = new Resume[storageMaxLenth];
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
        return (size == storageMaxLenth);
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            storage[i] = null;
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
        Resume[] res = new Resume[size];
        System.arraycopy(storage, 0, res, 0, size);
        return res;
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
