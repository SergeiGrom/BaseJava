package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(String uuid, Resume resume) {
        storage.replace(uuid, resume);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void saveResume(String uuid, Resume resume) {
        storage.put(uuid, resume);
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
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
