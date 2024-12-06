package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected final void deleteResume(Object index) {
        int size = size();
        storage[(int) index] = storage[size - 1];
        setSize(--size);
    }

    @Override
    protected final void saveResume(Object index, Resume resume) {
        checkOverflow(resume.getUuid());
        int size = size();
        storage[size] = resume;
        setSize(++size);
    }

    @Override
    protected final Object getKey(Object index) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(index)) {
                return i;
            }
        }
        return -1;
    }
}
