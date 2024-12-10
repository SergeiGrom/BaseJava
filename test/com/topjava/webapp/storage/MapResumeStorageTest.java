package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public class MapResumeStorageTest extends AllStorageTest {

    public MapResumeStorageTest() {
        super(new <Resume>MapResumeStorage());
    }
}