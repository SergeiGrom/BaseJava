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
        storage.put((Resume) key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((Resume) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((Resume) key);
    }

    @Override
    protected Object getKey(Object key) {
        return key;
    }

    @Override
    protected List<Resume> getAllSortedResumes(Comparator<Resume> comparator) {
        List<Resume> resumes = new ArrayList<>(storage.values());
        resumes.sort(comparator);
        return resumes;
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
