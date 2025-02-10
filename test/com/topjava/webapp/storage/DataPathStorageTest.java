package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AllStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new DataStreamSerializer()));
    }
}
