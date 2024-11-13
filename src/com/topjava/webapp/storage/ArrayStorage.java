package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int CAPACITY = 10000;
    private Resume[] storage = new Resume[CAPACITY];
    private int size;

    public void update(Resume resume) {
        String result = isExist(resume.getUuid()) ? resume + " UPDATED" : resume + " NOT FOUND";
        System.out.println(result);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (isExist(resume.getUuid())) {
            System.out.println(resume.getUuid() + " ALREADY EXISTS");
            return;
        }
        if (isFull()) {
            System.out.println("ERROR: Storage is Full");
            return;
        }
        if (!isExist(resume.getUuid()) && !isFull()) {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (isExist(uuid)) {
            for (Resume resume : getAll()) {
                if (Objects.equals(resume.getUuid(), uuid)) {
                    return resume;
                }
            }
        }
        System.out.println(uuid + " NOT FOUND");
        return null;
    }

    public void delete(String uuid) {
        if (isExist(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                    storage[size - 1] = null;
                    size--;
                }
            }
        } else {
            System.out.println(uuid + " NOT FOUND");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public boolean isExist(String uuid) {
        for (Resume resume : getAll()) {
            if (Objects.equals(resume.getUuid(), uuid)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return size == CAPACITY;
    }
}
