package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.replace(String.valueOf(key), resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get(String.valueOf(key));
    }

    @Override
    protected void saveResume(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(String.valueOf(key));
    }

    @Override
    protected boolean isExist(Object key) {
        Resume resume = (Resume) key;
        return storage.containsKey(resume.getUuid());
    }

    @Override
    protected Object getKey(Object key) {
        Resume resume = (Resume) key;
        return resume.getUuid();
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
