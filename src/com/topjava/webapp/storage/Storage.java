package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public interface Storage {

    int getSize();

    void update(Resume resume);

    Resume get(String uuid);

    void clear();

    Resume[] getAll();

    void save(Resume resume);

    void delete(String uuid);
}
