package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * SK - search key
 */
public abstract class AbstractStorage<SK> implements Storage {
    //    protected final Logger LOG = Logger.getLogger(getClass().getName());
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public final void update(Resume resume) {
        LOG.info("Update resume " + resume);
        SK searchKey = getExistingSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public final Resume get(String uuid) {
        LOG.info("Get resume " + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public final void save(Resume resume) {
        LOG.info("Save resume " + resume);
        SK searchKey = getNotExistingSearchKey(resume.getUuid());
        saveResume(searchKey, resume);
    }

    @Override
    public final void delete(String uuid) {
        LOG.info("Delete resume " + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        deleteResume(searchKey);
    }

    @Override
    public final List<Resume> getAllSorted() {
        LOG.info("Get all sorted resumes");
        List<Resume> list = getAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected final SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume uuid=" + uuid + " ALREADY EXIST");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected final SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume uuid=" + uuid + " NOT EXIST");
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
