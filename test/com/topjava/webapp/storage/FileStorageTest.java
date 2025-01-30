package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serializer.ObjectStreamSerializerStrategy;

public class FileStorageTest extends AllStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializerStrategy()));
    }
}
