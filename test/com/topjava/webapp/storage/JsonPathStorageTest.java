package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AllStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new JsonStreamSerializer()));
    }
}
