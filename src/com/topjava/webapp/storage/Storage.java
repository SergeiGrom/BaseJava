package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.List;

public interface Storage {

    int size();

    void update(Resume resume);

    Resume get(Object searchedKey);

    void clear();

    List<Resume> getAllSorted();

    void save(Resume resume);

    void delete(Object searchedKey);
}
