/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int storageMaxLenth = 10000;
    private Resume[] storage = new Resume[storageMaxLenth];
    private int sizeArray = 0;

    private int getIndex(String uuid) {
            for (int i = 0; i < sizeArray; i++) {
                if (uuid.equals(storage[i].uuid))
                    return i;
            }
        return -1;
    }

    private boolean isOverflow() {
        return (sizeArray == storageMaxLenth);
    }

    void clear() {
        for (int i = 0; i < sizeArray; i++)
            storage[i] = null;
        sizeArray = 0;
    }

    void save(Resume r) {
        if (isOverflow())
            System.out.println("Извините места в хранилище больше нет!");
        else {
            int index = getIndex(r.uuid);
            if (index < 0) {
                storage[sizeArray] = r;
                sizeArray++;
            } else
                System.out.println("Резюме с " + r + " уже есть!");
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
            System.arraycopy(storage, index + 1, storage, index, --sizeArray - index);
            System.out.println("Удаляемое резюме с uuid = " + uuid + " удалено из хранилища.");
        } else
            System.out.println("Удаляемое резюме с uuid = " + uuid + " не было обнаружено в хранилище.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[sizeArray];
        System.arraycopy(storage, 0, res, 0, sizeArray);
        return res;
    }

    int size() {
        return sizeArray;
    }
}
