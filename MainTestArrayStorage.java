import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for com.urise.webapp.storage.com.urise.webapp.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    private final static Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume();
        r1.setUuid("uuid6");
        final Resume r2 = new Resume();
        r2.setUuid("uuid2");
        final Resume r3 = new Resume();
        r3.setUuid("uuid5");
        final Resume r4 = new Resume();
        r4.setUuid("uuid4");
        final Resume r5 = new Resume();
        r5.setUuid("uuid3");
        final Resume r6 = new Resume();
        r6.setUuid("uuid1");

        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r6);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        if (ARRAY_STORAGE.update(ARRAY_STORAGE.get("uuid222")))
            System.out.println("Успех. Резюме "+ARRAY_STORAGE.get("uuid222")+" отредактированно.");
        else
            System.out.println("Неудача. Резюме "+ARRAY_STORAGE.get("uuid222")+" не было отредактированно.");
        printAll();
        if (ARRAY_STORAGE.update(ARRAY_STORAGE.get("uuid2")))
            System.out.println("Успех. Резюме "+ARRAY_STORAGE.get("uuid2")+" отредактированно.");
        else
            System.out.println("Неудача. Резюме "+ARRAY_STORAGE.get("uuid2")+" не было отредактированно.");

        printAll();
        if(ARRAY_STORAGE.delete(r2.getUuid()))
            System.out.println("Успех. Резюме "+r2+" удалено.");
        else
            System.out.println("Неудача. Резюме "+r2+" не было удалено.");

        printAll();
        System.out.println("Вызов метода clear.");
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\n---Get All:---");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
        System.out.println("------::------\n");
    }
}
