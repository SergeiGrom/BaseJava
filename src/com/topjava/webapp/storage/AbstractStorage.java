package com.topjava.webapp.storage;

import com.topjava.webapp.exception.*;
import com.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public final void update(Resume resume) {
        Object key = getKeyOrNotExistExc(resume.getUuid());
        updateResume(key, resume);
    }

    @Override
    public final Resume get(String uuid) {
        Object key = getKeyOrNotExistExc(uuid);
        return getResume(key);
    }

    @Override
    public final void save(Resume resume) {
        Object key = getKeyOrExistExc(resume.getUuid());
        saveResume(key, resume);
    }

    @Override
    public final void delete(String uuid) {
        Object key = getKeyOrNotExistExc(uuid);
        deleteResume(key);
    }

    protected final Object getKeyOrNotExistExc(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    protected final Object getKeyOrExistExc(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    protected abstract void updateResume(Object key, Resume resume);

    protected abstract Resume getResume(Object key);

    protected abstract void saveResume(Object key, Resume resume);

    protected abstract void deleteResume(Object key);

    protected abstract boolean isExist(String uuid);

    protected abstract Object getKey(String uuid);
}
