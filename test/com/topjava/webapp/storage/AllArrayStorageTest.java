package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AllArrayStorageTest extends AllStorageTest {

    public AllArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public final void checkOverflow() throws Exception {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume(""));
            }
        } catch (StorageException e) {
            Assert.fail("Overflow exception occurred before storage overflow");
        }
        storage.save(new Resume(""));
    }
}