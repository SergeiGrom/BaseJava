package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
        return new ArrayList<>(storage);
    }

    @Override
    protected final void updateResume(Integer index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected final Resume getResume(Integer index) {
        return storage.get(index);
    }

    @Override
    protected final void saveResume(Integer index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected final void deleteResume(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    protected final boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected final Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
