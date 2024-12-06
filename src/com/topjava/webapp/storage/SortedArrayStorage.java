package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

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
    protected final Object getKey(Object searchedKey) {
        int size = size();
        Resume searchedResume = new Resume(searchedKey.toString());
        return Arrays.binarySearch(storage, 0, size, searchedResume, RESUME_COMPARATOR);
    }

    /* EXAMPLES
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };
    */

    /* Introduce Lambda expression
    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());
    */
}