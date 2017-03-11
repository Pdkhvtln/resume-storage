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

/**
 * Created by Andrey on 02.01.2017.
 */
public abstract class AbstractStorageTest {
    protected static File STORAGE_PATH = Config.getInstance().getStorageDir();//new File("/home/andrew/IdeaProjects/resume-storage/storage/");
    //protected static final File STORAGE_FILE = new File(STORAGE_PATH);


    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String FULL_NAME_1 = "First Man";
    protected Resume RESUME_1;

    protected static final String UUID_2 = "uuid2";
    protected static final String FULL_NAME_2 = "Second Man";
    protected Resume RESUME_2;

    protected static final String UUID_3 = "uuid3";
    protected static final String FULL_NAME_3 = "Third Man";
    protected Resume RESUME_3;

    protected static final String UUID_X = "uuidX";
    protected static final String FULL_NAME_X = "X Man";
    protected Resume RESUME_X;

    @Before
    public void setUp() throws Exception {
        storage.clear();

        RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
        RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
        RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
        RESUME_X = new Resume(UUID_X, FULL_NAME_X);

 /*       RESUME_1.addContact(ContactType.MAIL, "java@u-rise.com");
        RESUME_1.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://URL1.com",
                                new Organization.Position(DateUtil.of(1993, Month.AUGUST), DateUtil.of(1996, Month.JULY), "Аспирантура", "Прогрммист C/C++"),
                                new Organization.Position(DateUtil.of(1987, Month.APRIL), DateUtil.of(1993, Month.DECEMBER), "Инженер", "Fortran, C")
                        )
                )
        );

        RESUME_1.addSection(SectionType.QUALIFICATIONS,
                new ListSection(
                        ("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"),
                        ("Version control: Subversion, Git, Mercury, ClearCase, Perforce"),
                        ("DB:PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB)")
                )
        );

        RESUME_2.addContact(ContactType.MAIL, "email2@mail.ru");
        RESUME_2.addContact(ContactType.SKYPE, "skype_us2");
        RESUME_2.addSection(SectionType.PERSONAL, new TextSection("Personal data 2"));

        RESUME_3.addContact(ContactType.MAIL, "email3@mail.ru");
        RESUME_3.addContact(ContactType.SKYPE, "us_skype3");
        RESUME_3.addSection(SectionType.PERSONAL, new TextSection("Personal data 3"));
        RESUME_3.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Place work number 1", "http://URL3.com",
                                new Organization.Position(DateUtil.of(2015, Month.DECEMBER), "Аспирантура", "Прогрммист C/C++")
                        )
                )
        );
*/
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