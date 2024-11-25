package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public class MapStorage extends AbstractStorage{
    @Override
    public int size() {
        return 0;
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }
}
