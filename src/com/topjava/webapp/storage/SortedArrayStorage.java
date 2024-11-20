package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        storage[size - 1] = null;
    }

    @Override
    protected void inputResume(int index, Resume resume) {
        int insPoint = -index - 1;
        System.arraycopy(storage, insPoint, storage, insPoint + 1, size - insPoint);
        storage[insPoint] = resume;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}