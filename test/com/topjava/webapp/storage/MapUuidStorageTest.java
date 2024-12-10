package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public class MapUuidStorageTest extends AllStorageTest {

    public MapUuidStorageTest() {
        super(new <Resume>MapUuidStorage());
    }
}