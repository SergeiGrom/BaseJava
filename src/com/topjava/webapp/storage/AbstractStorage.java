package com.topjava.webapp.storage;

import com.topjava.webapp.exception.*;
import com.topjava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

/**
 * SK - search key
 */
public abstract class AbstractStorage<SK> implements Storage {
    public final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public final void update(Resume resume) {
        SK searchKey = getExistingSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public final Resume get(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public final void save(Resume resume) {
        SK searchKey = getNotExistingSearchKey(resume.getUuid());
        saveResume(searchKey, resume);
    }

    @Override
    public final void delete(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        deleteResume(searchKey);
    }

    @Override
    public final List<Resume> getAllSorted() {
        List<Resume> list = getAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected final SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected final SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract List<Resume> getAll();

    protected abstract void updateResume(SK searchKey, Resume resume);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void saveResume(SK searchKey, Resume resume);

    protected abstract void deleteResume(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);
}
