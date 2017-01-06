package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Andrey on 06.01.2017.
 */
public class ListStorage extends AbstractStorage {
    private Collection<Resume> list;

    public ListStorage() {
        list = new ArrayList<Resume>();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void save(Resume r) {
        if (list.contains(r))
            throw new ExistStorageException(r.getUuid());
        else
            list.add(r);
    }

    @Override
    public Resume get(String uuid) {
        Resume r = new Resume(uuid);
        if (list.contains(r)) {
            //return list.get(list.indexOf(r));
            Iterator<Resume> iterator = list.iterator();
            while (iterator.hasNext()) {
                Resume result = iterator.next();
                if (r.equals(result))
                    return result;
            }
        } else
            throw new NotExistStorageException(uuid);
        return null;
    }

    @Override
    public void delete(String uuid) {
        Resume r = new Resume(uuid);
        if (list.contains(r)) {
            list.remove(r);
        } else
            throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume rArr[] = new Resume[size()];
        int i = 0;
        Iterator<Resume> iterator = list.iterator();
        while (iterator.hasNext()) {
            rArr[i] = iterator.next();
            i++;
        }
        return rArr;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void update(Resume r) {
        if (list.contains(r)) {
            //list.set(list.indexOf(r), r);
            Iterator<Resume> iterator = list.iterator();
            while (iterator.hasNext()) {
                Resume result = iterator.next();
                if (r.equals(result)) {
                    iterator.remove();
                }
            }
            list.add(r);
        } else
            throw new NotExistStorageException(r.getUuid());
    }
}

