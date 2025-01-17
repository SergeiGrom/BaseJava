package com.topjava.webapp.storage;

public class ObjectStreamStorageTest extends AllStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}
