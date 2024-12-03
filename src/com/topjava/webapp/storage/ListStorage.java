package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public final int size() {
        return storage.size();
    }

    @Override
    public final Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public final void clear() {
        storage.clear();
    }

    @Override
    protected final void updateResume(Object key, Resume resume) {
        storage.set((int) key, resume);
    }

    @Override
    protected final Resume getResume(Object key) {
        return storage.get((int) key);
    }

    @Override
    protected final void saveResume(Object key, Resume resume) {
        checkOverflow(resume.getUuid());
        storage.add(resume);
    }

    @Override
    protected final void deleteResume(Object key) {
        storage.remove((int) key);
    }

    @Override
    protected final boolean isExist(String uuid) {
        int index = (int) getKey(uuid);
        return index >= 0;
    }

    @Override
    protected final Object getKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected final void checkOverflow(String uuid) {
        if (size() == Integer.MAX_VALUE) {
            throw new StorageException("Storage overflow", uuid);
        }
    }
}
