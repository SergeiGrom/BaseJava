package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.util.*;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int CAPACITY = 10000;
    protected final Resume[] storage = new Resume[CAPACITY];
    private static int size;

    public static void setSize(int size) {
        AbstractArrayStorage.size = size;
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected final List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected final void updateResume(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    protected final Resume getResume(Object index) {
        return storage[(int) index];
    }

    @Override
    protected final boolean isExist(Object index) {
        return (int) index >= 0;
    }

    protected final void checkOverflow(String uuid) {
        if (size >= CAPACITY) {
            throw new StorageException("Storage overflow", uuid);
        }
    }
}
