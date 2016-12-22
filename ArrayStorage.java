/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final int storageMaxLenth = 10000;
    Resume[] storage = new Resume[storageMaxLenth];
    private int sizeArray = 0;

    private int getIndex(String uuid) {
        for (int i = 0; i < sizeArray; i++) {
            if (storage[i].uuid.equals(uuid))
                return i;
        }
        return -1;
    }

    private boolean isOverflow() {
        if (sizeArray == storageMaxLenth)
            return true;
        else
            return false;
    }

    void clear() {
        for (int i = 0; i < sizeArray; i++)
            storage[i] = null;
        sizeArray = 0;
    }

    void save(Resume r) {
        if (!isOverflow()) {
            System.out.println("Извините места в хранилище больше нет!");
            return;
        }
        int index = getIndex(r.uuid);
        if (index < 0) {
            storage[sizeArray] = r;
            sizeArray++;
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
            for (int i = index; i < sizeArray - 1; i++)
                storage[i] = storage[i + 1];
            sizeArray--;
            System.out.println("Удаляемое резюмес uuid = " + uuid + " удалено из хранилища.");
        } else
            System.out.println("Удаляемое резюмес uuid = " + uuid + " не было обнаружено в хранилище.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (sizeArray > 0) {
            Resume[] res = new Resume[sizeArray];
            for (int i = 0; i < sizeArray; i++)
                res[i] = storage[i];
        }
        return null;
    }

    int size() {
        return sizeArray;
    }
}
