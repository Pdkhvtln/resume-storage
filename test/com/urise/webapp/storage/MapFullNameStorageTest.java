package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Andrey on 17.01.2017.
 */
public class MapFullNameStorageTest extends AbstractStorageTest {
    public MapFullNameStorageTest() {
        super(new MapFullNameStorage());
    }

    @Override
    public void delete() throws Exception {
        int sz = storage.size();
        List<Resume> copyStorage = storage.getAllSorted();
        storage.delete(FULL_NAME_2);
        assertSize(sz - 1);
        Assert.assertNotEquals(copyStorage.toArray(), storage.getAllSorted().toArray());
    }

    @Override
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, FULL_NAME_1);
        storage.update(newResume);
        Assert.assertTrue(newResume == storage.get(FULL_NAME_1));
    }

    @Override
    public void save() throws Exception {
        int sz = storage.size();
        storage.save(new Resume(UUID_4, FULL_NAME_4));
        assertSize(sz + 1);
        Assert.assertEquals(RESUME_4, storage.get(FULL_NAME_4));
    }

    @Override
    public void get() throws Exception {
        Assert.assertEquals(RESUME_1, storage.get(FULL_NAME_1));
        Assert.assertEquals(RESUME_2, storage.get(FULL_NAME_2));
        Assert.assertEquals(RESUME_3, storage.get(FULL_NAME_3));
    }
}