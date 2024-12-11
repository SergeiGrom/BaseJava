package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.List;

public interface Storage {

    int size();

    void update(Resume resume);

    Resume get(String uuid);

    void clear();

    List<Resume> getAllSorted();

    void save(Resume resume);

    void delete(String uuid);
}
