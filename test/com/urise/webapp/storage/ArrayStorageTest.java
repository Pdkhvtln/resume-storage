package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;

/**
 * Created by Andrey on 02.01.2017.
 */
public class ArrayStorageTest extends AbstractArrayStorageTest {
    @Before
    public void setUp() throws Exception {
        storage = new ArrayStorage();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }
}