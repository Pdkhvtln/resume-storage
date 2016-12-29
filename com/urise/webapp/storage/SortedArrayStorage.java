package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Created by Andrey on 28.12.2016.
 */

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public boolean save(Resume r) {
        if (isOverflow()) {
            System.out.println("Извините места в хранилище больше нет!");
            return false;
        }
        if (Resume.isResume(r)) {
            int index = getIndex(r.getUuid());
            if (index < 0) {//The resume with such uuid did not find in Storage, will add it

                if (size == 0)//Storage is empty
                {
                    storage[size] = r;
                    size++;
                    return true;//success
                } else {//Search the place for insert
                    int leftBound = 0;
                    int rightBound = size;
                    int searchPlaceForInsert = (int) (leftBound + rightBound) / 2;
                    while ((rightBound - leftBound) != 1) {
                        int resultCompare = storage[searchPlaceForInsert].compareTo(r);
                        if (resultCompare <= -1)//storage[searchPlaceForInsert]<r
                            leftBound = searchPlaceForInsert;
                        if (resultCompare >= 1)//storage[searchPlaceForInsert]>r
                            rightBound = searchPlaceForInsert;
                        searchPlaceForInsert = (int) (leftBound + rightBound) / 2;
                    }
                    //Insert the resume in the storage
                    System.arraycopy(storage, rightBound, storage, rightBound + 1, ++size - rightBound);
                    storage[rightBound] = r;
                    Arrays.sort(storage, leftBound, rightBound + 1);
                    return true;//success
                }
            } else
                System.out.println("Резюме с " + r + " уже есть!");
        } else
            System.out.println("Невозможно добавить в хранилище null значение!");
        return false;//failure
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
