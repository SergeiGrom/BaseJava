package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(Object index) {
        int size = size();
        storage[(int) index] = storage[size - 1];
        setSize(--size);
    }

    @Override
    protected void saveResume(Object index, Resume resume) {
        checkOverflow(resume.getUuid());
        int size = size();
        storage[size] = resume;
        setSize(++size);
    }

    @Override
    protected Object getKey(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
