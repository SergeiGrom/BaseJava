package com.topjava.webapp.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String resume) {
        super(resume + " ALREADY EXIST", resume);
    }
}
