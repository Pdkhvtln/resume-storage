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
