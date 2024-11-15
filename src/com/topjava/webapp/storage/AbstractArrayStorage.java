package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected final int CAPACITY = 10000;
    protected final Resume[] storage = new Resume[CAPACITY];
    protected int size;

    public int getSize() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println(uuid + " NOT FOUND");
        return null;
    }

    protected abstract int getIndex(String uuid);

}
