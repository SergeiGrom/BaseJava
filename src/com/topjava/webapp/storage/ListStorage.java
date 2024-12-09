package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public final int size() {
        return storage.size();
    }

    @Override
    public final void clear() {
        storage.clear();
    }

    @Override
    protected final List<Resume> getAll() {
        List<Resume> listStorage = new ArrayList<>(storage);
        listStorage.sort(RESUME_COMPARATOR);
        return listStorage;
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
        storage.add(resume);
    }

    @Override
    protected final void deleteResume(Object key) {
        storage.remove((int) key);
    }

    @Override
    protected final boolean isExist(Object key) {
        int index = (int) getKey(key);
        return index >= 0;
    }

    @Override
    protected final Object getKey(Object key) {
        Resume resume = (Resume) key;
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(resume.getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
