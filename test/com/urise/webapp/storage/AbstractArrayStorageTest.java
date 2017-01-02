package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by Andrey on 02.01.2017.
 */
public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
        Assert.assertEquals(new Resume(UUID_3), storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] r = storage.getAll();
        Assert.assertEquals(3, r.length);
        Assert.assertEquals(new Resume(UUID_1), r[0]);
        Assert.assertEquals(new Resume(UUID_2), r[1]);
        Assert.assertEquals(new Resume(UUID_3), r[2]);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        int sz = storage.size();
        Resume[] copyStorage = storage.getAll();
        storage.save(new Resume("uuid10"));
        Assert.assertEquals(sz + 1, storage.size());
        Assert.assertNotEquals(copyStorage, storage.getAll());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        int sz = storage.size();

        Class cls = storage.getClass().getSuperclass();
        Field field = cls.getDeclaredField("size");
        field.setAccessible(true);
        field.set(storage, 10000);

        storage.save(new Resume("uuid10001"));

        field.set(storage, sz);
        field.setAccessible(false);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("uuid9"));
    }

    @Test
    public void delete() throws Exception {
        int sz = storage.size();
        Resume[] copyStorage = storage.getAll();
        storage.delete(UUID_2);
        Assert.assertEquals(sz - 1, storage.size());
        Assert.assertNotEquals(copyStorage, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        Resume[] copyStorage = storage.getAll();
        storage.delete("dummy");
        Assert.assertArrayEquals(copyStorage, storage.getAll());
    }
}