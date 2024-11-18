package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final int CAPACITY = 10000;
    protected final Resume[] storage = new Resume[CAPACITY];
    protected int size;

    public int getSize() {
        return size;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println(uuid + "NOT FOUND");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[--size] = null;
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println(uuid + "NOT FOUND");
        } else {
            storage[index] = resume;
            System.out.println(uuid + " UPDATED");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println(uuid + "NOT FOUND");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected boolean canSave(Resume resume) {
        String uuid = resume.getUuid();
        if (getIndex(uuid) >= 0) {
            System.out.println(uuid + " ALREADY EXISTS");
            return false;
        }
        if (isFull()) {
            System.out.println("ERROR: STORAGE IS FULL");
            return false;
        }
        return true;
    }

    protected boolean isFull() {
        return size == CAPACITY;
    }

    protected abstract void save(Resume resume);

    protected abstract int getIndex(String uuid);
}
