package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    protected Map<Resume, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.replace((Resume) key, resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((Resume) key);
    }

    @Override
    protected void saveResume(Object key, Resume resume) {
        storage.put(resume, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((Resume) key);
    }

    @Override
    protected boolean isExist(Object searchedKey) {
        if (searchedKey == null) {
            throw new NullPointerException("Search key in Map is null");
        }
        return storage.containsKey((Resume) searchedKey);
    }

    @Override
    protected Object getKey(Object searchedKey) {
        return storage.get((Resume) searchedKey);
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> listStorage = new ArrayList<>(storage.values());
        listStorage.sort(RESUME_COMPARATOR);
        return listStorage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
