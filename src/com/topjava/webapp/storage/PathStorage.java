package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected PathStorage(String dir) {
        directory = Paths.get(dir);
        this.streamSerializer = new ObjectStreamSerializerStrategy();
        Objects.requireNonNull(dir, "directory name must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not a directory or is not readable/writable");
        }
    }

    @Override
    protected List<Resume> getAll() {
        return listFiles().filter(Files::isRegularFile)
                .map(this::getResume)
                .collect(Collectors.toList());
    }

    @Override
    protected void updateResume(Path path, Resume resume) {
        try {
            streamSerializer.writeResume(new BufferedOutputStream(Files.newOutputStream(path)), resume);
        } catch (IOException e) {
            throw new StorageException(path + " WRITE ERROR", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return streamSerializer.readResume(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException(path + " READ ERROR", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void saveResume(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException(path + " WRITE ERROR", null, e);
        }
        updateResume(path, resume);
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException(path + " DELETE ERROR", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    public int size() {
        return (int) listFiles().count();
    }

    @Override
    public void clear() {
        listFiles().forEach(this::deleteResume);
    }

    private Stream<Path> listFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException(directory + " READ ERROR", null);
        }
    }
}
