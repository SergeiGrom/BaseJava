package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serializer.ObjectStreamSerializerStrategy;

public class PathStorageTest extends AllStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new ObjectStreamSerializerStrategy()));
    }
}
