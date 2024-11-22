package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int CAPACITY = 10000;
    protected final Resume[] storage = new Resume[CAPACITY];
    protected int size;

    public int getSize() {
        return size;
    }

    public final void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
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
            throw new StorageException("STORAGE OVERFLOW", resume.getUuid());
        }
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        } else {
            inputResume(index, resume);
            size++;
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void deleteResume(int index);

    protected abstract void inputResume(int index, Resume resume);

    protected abstract int getIndex(String uuid);
}
