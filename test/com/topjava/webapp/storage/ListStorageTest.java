package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public class ListStorageTest extends AllStorageTest {

    public ListStorageTest() {
        super(new <Resume>ListStorage());
    }
}