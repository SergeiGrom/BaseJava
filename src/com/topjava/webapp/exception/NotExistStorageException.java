package com.topjava.webapp.exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String resume) {
        super(resume + " NOT EXIST", resume);
    }
}
