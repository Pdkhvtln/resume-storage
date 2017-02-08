package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrew on 08.02.17.
 */
public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalAccessException(directory.getAbsolutePath() + " is not directory")
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalAccessException(directory.getAbsolutePath() + " is not readable/writeable")
        }
        this.directory = directory;
    }

    @Override
    public void clear() {//получить вс файлы и удалить их из каталога

    }

    @Override
    public int size() {//посчитать количество файлов в каталоге
        return 0;
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r,file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), IOException e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    protected void doUpdate(Resume r, File file) {//тоже что и в create только файл не надо создавать

    }

    @Override
    protected void doDelete(File existedfile) {

    }

    @Override
    protected Resume doGet(File file) {
        return null;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory,uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }
}
