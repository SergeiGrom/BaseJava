package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public class MapStorage extends AbstractStorage{
    @Override
    protected void updateResume(Object key, Resume resume) {

    }

    @Override
    protected Resume getResume(Object key) {
        return null;
    }

    @Override
    protected void saveResume(Object key, Resume resume) {

    }

    @Override
    protected void deleteResume(Object key) {

    }

    @Override
    protected boolean isExist(String uuid) {
        return false;
    }

    @Override
    protected Object getKey(String uuid) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
