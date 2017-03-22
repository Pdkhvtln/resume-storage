package com.urise.webapp.utils;

import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by andrew on 11.03.17.
 */
public class Config {
    private static final String PROPERTIES_PATH = "/resumes.properties";
    private static final Config INSTANCE = new Config();
    private File storageDir;
    private Storage storage;

    public File getStorageDir() {
        return storageDir;
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SqlStorage(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPERTIES_PATH);
        }
    }

    public Storage getStorage() {
        return storage;
    }

}
