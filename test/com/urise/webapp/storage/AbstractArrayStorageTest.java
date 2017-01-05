package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Andrey on 02.01.2017.
 */
public abstract class AbstractArrayStorageTest {
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected Resume RESUME_1 = new Resume(UUID_1);

    protected static final String UUID_2 = "uuid2";
    protected Resume RESUME_2 = new Resume(UUID_2);

    protected static final String UUID_3 = "uuid3";
    protected Resume RESUME_3 = new Resume(UUID_3);

    protected static final String UUID_4 = "uuid4";
    protected Resume RESUME_4 = new Resume(UUID_4);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
        Assert.assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] r = storage.getAll();
        Assert.assertEquals(3, r.length);
        Assert.assertEquals(RESUME_1, r[0]);
        Assert.assertEquals(RESUME_2, r[1]);
        Assert.assertEquals(RESUME_3, r[2]);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws Exception {
        int sz = storage.size();
        storage.save(new Resume(UUID_4));
        assertSize(sz + 1);
        Assert.assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        final int i=storage.size();
        System.out.println("size = "+i);
        try {
            for (int j = i; j < AbstractArrayStorage.STORAGE_MAX_LENGTH; j++) {
                storage.save(new Resume());
                if(storage.size()>9995)
                    System.out.println(storage.size());
            }
        } catch (Exception e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assert.assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void delete() throws Exception {
        int sz = storage.size();
        Resume[] copyStorage = storage.getAll();
        storage.delete(UUID_2);
        assertSize(sz - 1);
        Assert.assertNotEquals(copyStorage, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        Resume[] copyStorage = storage.getAll();
        storage.delete("dummy");
        Assert.assertArrayEquals(copyStorage, storage.getAll());
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}