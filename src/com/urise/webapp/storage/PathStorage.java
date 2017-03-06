package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrew on 08.02.17.
 */
public class PathStorage extends ObjectStreamStorage<Path> {
    private Path directory;

    protected PathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writeable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach((Path path) -> {
                doDelete(path);
            });
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {//посчитать количество файлов в каталоге
        try {
            return (int) Files.list(directory).count();//(int) Files.size(directory);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + path.toString(), path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {//тоже что и в create только файл не надо создавать
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid());
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            if (Files.exists(path))
                Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error", path.toFile().getName(), e);
        }
    }


    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File read error", path.toFile().getName(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toString(), uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {
        /*
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }*/
        List<Resume> list = new ArrayList<>(size());
        //for (File file : directory.listFiles())
        try {
            Files.list(directory).forEach((Path path) -> {
                list.add(doGet(path));
            });
        } catch (IOException e) {
            throw new StorageException("Path doCopyAll error", null, e);
        }
        return list;
    }

}
