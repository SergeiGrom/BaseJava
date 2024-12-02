package com.topjava.webapp.storage;

import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        try {
            storage.get(RESUME_1.getUuid());
            storage.get(RESUME_2.getUuid());
            storage.get(RESUME_3.getUuid());
        } catch (NotExistStorageException e) {
            Assert.fail("No Resumes to test Clear");
        }
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Storage[0], storage.getAll());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test(expected = StorageException.class)
    public final void checkOverflow() throws Exception {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Overflow exception occurred before storage overflow");
        }
        storage.save(new Resume());
    }
}