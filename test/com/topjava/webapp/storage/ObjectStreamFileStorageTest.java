package com.topjava.webapp.storage;

public class ObjectStreamFileStorageTest extends AllStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR));
    }
}
