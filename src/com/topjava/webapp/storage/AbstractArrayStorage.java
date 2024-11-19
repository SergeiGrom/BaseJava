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

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (size == CAPACITY) {
            System.out.println("ERROR: STORAGE IS FULL");
            return;
        }
        if (index >= 0) {
            System.out.println(uuid + " ALREADY EXISTS");
        } else {
            inputResume(index, resume);
            size++;
        }
    }

     public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println(uuid + "NOT FOUND");
        } else {
            deleteResume(index);
            size--;
        }
    }

    protected abstract void deleteResume(int index);

    protected abstract void inputResume(int index, Resume resume);

    protected abstract int getIndex(String uuid);
}
