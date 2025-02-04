package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serializer.XmlStreamSerializer;
public class XmlPathStorageTest extends AllStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new XmlStreamSerializer()));
    }
}
