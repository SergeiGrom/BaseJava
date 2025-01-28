package com.topjava.webapp.storage;

public class FileStorageTest extends AllStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR));
    }
}
