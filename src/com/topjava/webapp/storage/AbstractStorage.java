package com.topjava.webapp.storage;

import com.topjava.webapp.exception.*;
import com.topjava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    public final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public final void update(Resume resume) {
        Object key = getExistingSearchKey(resume);
        updateResume(key, resume);
    }

    @Override
    public final Resume get(Object searchedKey) {
        Object key = getExistingSearchKey(searchedKey);
        return getResume(key);
    }

    @Override
    public final void save(Resume resume) {
        Object key = getNotExistingSearchKey(resume);
        saveResume(key, resume);
    }

    @Override
    public final void delete(Object searchedKey) {
        Object key = getExistingSearchKey(searchedKey);
        deleteResume(key);
    }

    @Override
    public final List<Resume> getAllSorted() {
        return getAll();
    }

    protected final Object getNotExistingSearchKey(Object searchedKey) {
        if (isExist(searchedKey)) {
            throw new ExistStorageException(searchedKey.toString());
        }
        return getKey(searchedKey);
    }

    protected final Object getExistingSearchKey(Object searchedKey) {
        if (!isExist(searchedKey)) {
            throw new NotExistStorageException(searchedKey.toString());
        }
        return getKey(searchedKey);
    }

    protected abstract List<Resume> getAll();

    protected abstract void updateResume(Object key, Resume resume);

    protected abstract Resume getResume(Object key);

    protected abstract void saveResume(Object key, Resume resume);

    protected abstract void deleteResume(Object key);

    protected abstract boolean isExist(Object searchedKey);

    protected abstract Object getKey(Object searchedKey);
}
