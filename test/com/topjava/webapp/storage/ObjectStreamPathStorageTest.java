package com.topjava.webapp.storage;

public class ObjectStreamPathStorageTest extends AllStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR));
    }
}
