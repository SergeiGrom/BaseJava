package com.topjava.webapp.storage;

import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new <Resume>ListStorage());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
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
    public void getAll() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(expected, storage.getAll());
    }
}