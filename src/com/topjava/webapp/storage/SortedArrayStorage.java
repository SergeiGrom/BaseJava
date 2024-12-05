package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

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
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}