package com.topjava.webapp.storage;

import com.topjava.webapp.Config;

public class SqlStorageTest extends AllStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getUrl(),Config.get().getUser(), Config.get().getPassword()));
    }
}
