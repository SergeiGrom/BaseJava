package com.topjava.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume UUID = [" + uuid + "] NOT EXIST", uuid);
    }
}
