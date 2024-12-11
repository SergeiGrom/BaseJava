package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public final int size() {
        return storage.size();
    }

    @Override
    public final void clear() {
        storage.clear();
    }

    @Override
    protected final List<Resume> getAll() {
        List<Resume> listStorage = new ArrayList<>(storage);
        listStorage.sort(RESUME_COMPARATOR);
        return listStorage;
    }

    @Override
    protected final void updateResume(Object index, Resume resume) {
        storage.set((int) index, resume);
    }

    @Override
    protected final Resume getResume(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected final void saveResume(Object index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected final void deleteResume(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected final boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected final Object getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
