package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Andrey on 20.01.2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapFullNameStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        SortedArrayStorageTest.class

})
public class AllStorageTest {
}