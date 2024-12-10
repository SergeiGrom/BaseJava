package com.topjava.webapp.exception;

public class StorageException extends RuntimeException {
    private final String resume;

    public StorageException(String message, String resume) {
        super(message);
        this.resume = resume;
    }
}