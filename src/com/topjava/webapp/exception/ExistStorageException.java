package com.topjava.webapp.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("Resume uuid=" + uuid + " ALREADY EXIST", uuid);
    }
}
