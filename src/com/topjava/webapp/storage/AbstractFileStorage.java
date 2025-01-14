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
        try {
            writeResume(file, resume);
        } catch (IOException e) {
            throw new StorageException("Save error", file.getName());
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return readResume(file);
        } catch (IOException e) {
            throw new StorageException("Read error", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(File file, Resume resume) {
        try {
            file.createNewFile();
            writeResume(file, resume);
        } catch (IOException e) {
            throw new StorageException("Save error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("Delete error", file.getName());
        }
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
        return Objects.requireNonNull(directory.listFiles(), "Read error").length;
    }

    @Override
    public void clear() {
        for (File file : Objects.requireNonNull(directory.listFiles(), "Read error")) {
            deleteResume(file);
        }
    }

    protected abstract void writeResume(File file, Resume resume) throws IOException;

    protected abstract Resume readResume(File file) throws IOException;
}
