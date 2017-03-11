package com.urise.webapp.utils;

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
    private static final File PROPERTIES_PATH = new File("config/resumes.properties");//./config/resumes.properties");
    private static final Config INSTANCE = new Config();
    private final Properties properties = new Properties();
    private File storageDir;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;


    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPERTIES_PATH.getAbsolutePath());
        }
    }
}
