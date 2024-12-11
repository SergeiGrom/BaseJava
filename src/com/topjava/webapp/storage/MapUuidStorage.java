package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Object uuid, Resume resume) {
        storage.replace(uuid.toString(), resume);
    }

    @Override
    protected Resume getResume(Object uuid) {
        return storage.get(uuid.toString());
    }

    @Override
    protected void saveResume(Object uuid, Resume resume) {
        storage.put(uuid.toString(), resume);
    }

    @Override
    protected void deleteResume(Object uuid) {
        storage.remove(uuid.toString());
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid.toString());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
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
