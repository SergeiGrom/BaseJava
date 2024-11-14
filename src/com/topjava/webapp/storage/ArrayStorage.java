package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int CAPACITY = 10000;
    private Resume[] storage = new Resume[CAPACITY];
    private int size;

    public int getSize() {
        return size;
    }

    public void save(Resume resume) {
        if (isFull()) {
            System.out.println("ERROR: Storage is Full");
            return;
        }
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println(resume.getUuid() + " ALREADY EXISTS");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) >= 0) {
            return storage[getIndex(uuid)];
        }
        System.out.println(uuid + " NOT FOUND");
        return null;
    }

    public void update(Resume resume) {
        String result = getIndex(resume.getUuid()) >= 0 ? resume + " UPDATED" : resume + " NOT FOUND";
        System.out.println(result);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println(uuid + " NOT FOUND");
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    private boolean isFull() {
        return size == CAPACITY;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
