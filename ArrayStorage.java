/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final int storageMaxLenth = 10000;
    Resume[] storage = new Resume[storageMaxLenth];

    private int getIndex(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid))
                return i;
        }
        return -1;
    }

    private boolean isOverflow() {
        if (storage.length == storageMaxLenth)
            return true;
        else
            return false;
    }

    void clear() {
        for (int i = 0; i < storage.length; i++)
            storage[i] = null;
    }

    void save(Resume r) {
        if (!isOverflow()) {
            System.out.println("Извините места больше нет!");
            return;
        }
        int index = getIndex(r.uuid);
        if (index < 0) {
            int i = storage.length;
            storage[i] = r;
        } else {
            System.out.println("Резюме с " + r + " уже есть!");
            return;
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//резюме найдено, возвращаем результат
            return storage[index];
        }
        //если резюме с таким uuid нет в БД
        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {//резюме найдено, возвращаем результат
            storage[index] = null;
        } else
            System.out.println("Удаляемое резюмес uuid = " + uuid + " не было обнаружено в БД.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[storage.length];
        for (int i = 0; i < storage.length; i++) {
            res[i] = storage[i];
        }
        return null;
    }

    int size() {
        return storage.length;
    }
}
