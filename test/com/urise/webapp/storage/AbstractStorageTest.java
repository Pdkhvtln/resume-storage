package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.utils.Config;
import com.urise.webapp.utils.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.*;

import static com.urise.webapp.TestData.*;


/**
 * Created by Andrey on 02.01.2017.
 */
public abstract class AbstractStorageTest {
    protected static File STORAGE_PATH = Config.getInstance().getStorageDir();//new File("/home/andrew/IdeaProjects/resume-storage/storage/");

    protected Storage storage;


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
        List<Resume> list= Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(list);
        Assert.assertEquals(r, list);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws Exception {
        int sz = storage.size();
        storage.save(new Resume(UUID_X, FULL_NAME_X));
        assertSize(sz + 1);
        Assert.assertEquals(RESUME_X, storage.get(UUID_X));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, FULL_NAME_1);
        RESUME_1.addContact(ContactType.MAIL, "update@u-rise.com");
        RESUME_1.addContact(ContactType.SKYPE, "NEW_SKYPE");
        RESUME_1.addContact(ContactType.PHONE, "+38 066 777 - 333 - 00");

        storage.update(newResume);
        Assert.assertTrue(newResume.equals(storage.get(UUID_1)));
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