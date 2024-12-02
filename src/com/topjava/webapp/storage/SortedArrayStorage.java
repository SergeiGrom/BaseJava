package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected final void deleteResume(Object index) {
        int size = size();
        System.arraycopy(storage, (int) index + 1, storage, (int) index, size - 1 - (int) index);
        setSize(--size);
    }

    @Override
    protected final void saveResume(Object index, Resume resume) {
        checkOverflow(resume.getUuid());
        int size = size();
        int insPoint = -(int) index - 1;
        System.arraycopy(storage, insPoint, storage, insPoint + 1, size - insPoint);
        storage[insPoint] = resume;
        setSize(++size);
    }

    @Override
    protected final Object getKey(String uuid) {
        int size = size();
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}