package com.topjava.webapp.storage;

public class PathStorageTest extends AllStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath()));
    }
}
