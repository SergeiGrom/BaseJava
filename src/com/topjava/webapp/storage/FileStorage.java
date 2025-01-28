package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;

    protected FileStorage(File directory) {
        Objects.requireNonNull(directory, "directory name must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.streamSerializer = new ObjectStreamSerializerStrategy();
    }

    @Override
    protected List<Resume> getAll() {
        return Arrays.stream(listFiles()).map(this::getResume).collect(Collectors.toCollection(() -> new ArrayList<>(size())));
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        try {
            streamSerializer.writeResume(new FileOutputStream(file), resume);
        } catch (IOException e) {
            throw new StorageException("WRITE ERROR", file.getName());
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return streamSerializer.readResume(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("READ ERROR", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("WRITE ERROR", file.getName(), e);
        }
        updateResume(file, resume);
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("DELETE ERROR", file.getName());
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
        return listFiles().length;
    }

    @Override
    public void clear() {
        Arrays.stream(listFiles()).forEach(this::deleteResume);
    }

    private File[] listFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException(directory + " READ ERROR", null);
        }
        return files;
    }
}
