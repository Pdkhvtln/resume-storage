package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrey on 02.01.2017.
 */
public abstract class AbstractStorageTest {
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String FULL_NAME_1 = "First Man";
    protected Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);

    protected static final String UUID_2 = "uuid2";
    protected static final String FULL_NAME_2 = "Second Man";
    protected Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);

    protected static final String UUID_3 = "uuid3";
    protected static final String FULL_NAME_3 = "Third Man";
    protected Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    protected static final String UUID_4 = "uuid4";
    protected static final String FULL_NAME_4 = "Fourth Man";
    protected Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    public AbstractStorageTest(Storage storage) {
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
    public void getAllSored() throws Exception {
        List<Resume> r = storage.getAllSorted();
        Assert.assertEquals(3, r.size());
        Assert.assertEquals(r, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws Exception {
        int sz = storage.size();
        storage.save(new Resume(UUID_4, FULL_NAME_4));
        assertSize(sz + 1);
        Assert.assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, FULL_NAME_1);
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
        List<Resume> copyStorage = storage.getAllSorted();
        storage.delete(UUID_2);
        assertSize(sz - 1);
        Assert.assertNotEquals(copyStorage.toArray(), storage.getAllSorted().toArray());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        List<Resume> copyStorage = storage.getAllSorted();
        storage.delete("dummy");
        Assert.assertArrayEquals(copyStorage.toArray(), storage.getAllSorted().toArray());
    }

    protected void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}