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
        Object searchKey = getExistingSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public final void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        saveResume(searchKey, resume);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        deleteResume(searchKey);
    }

    @Override
    public final List<Resume> getAllSorted() {
        return getAll();
    }

    protected final Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected final Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract List<Resume> getAll();

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void saveResume(Object searchKey, Resume resume);

    protected abstract void deleteResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);
}
