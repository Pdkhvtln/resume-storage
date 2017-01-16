package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Andrey on 12.01.2017.
 */
public abstract class AbstractArrayTest extends AbstractStorageTest{

    public AbstractArrayTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        final int i=storage.size();
        try {
            for (int j = i; j < AbstractArrayStorage.STORAGE_MAX_LENGTH; j++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}
