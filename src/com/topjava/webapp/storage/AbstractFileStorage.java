package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory name must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getAll() {
        return List.of();
    }

    @Override
    protected void updateResume(File file, Resume resume) {

    }

    @Override
    protected Resume getResume(File file) {
        return null;
    }

    @Override
    protected void saveResume(File file, Resume resume) {
        try {
            file.createNewFile();
            writeResume(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    private void writeResume(File file, Resume resume) {

    }

    @Override
    protected void deleteResume(File file) {

    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
