package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public interface Storage {

    int getSize();

    void save(Resume resume);

    Resume get(String uuid);

    void update(Resume resume);

    void delete(String uuid);

    void clear();

    Resume[] getAll();
}
