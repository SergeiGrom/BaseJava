package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected final void deleteResume(Integer index) {
        int size = size();
        storage[ index] = storage[size - 1];
        setSize(--size);
    }

    @Override
    protected final void saveResume(Integer index, Resume resume) {
        checkOverflow(resume.getUuid());
        int size = size();
        storage[size] = resume;
        setSize(++size);
    }

    @Override
    protected final Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
