package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void getSize() throws Exception {
        Assert.assertEquals(3, storage.getSize());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        Assert.assertEquals(storage.get(UUID_1), resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        for (Resume resume : storage.getAll()) {
            Assert.assertNull(resume);
        }
    }

    @Test
    public void clearGetSizeZero() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.getSize());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] testArray = storage.getAll();
        Assert.assertEquals(testArray[0], RESUME_1);
        Assert.assertEquals(testArray[1], RESUME_2);
        Assert.assertEquals(testArray[2], RESUME_3);
        Assert.assertEquals(3, testArray.length);
    }

    @Test(expected = StorageException.class)
    public final void saveTestOverflow() throws Exception {
        try {
            for (int i = storage.getSize(); i <= AbstractArrayStorage.CAPACITY - 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Overflow exception occurred before storage overflow");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveGetExistStorageException() throws Exception {
        storage.save(RESUME_1);
    }

    @Test
    public void saveGetNewSize() throws Exception {
        storage.save(RESUME_4);
        Assert.assertEquals(4, storage.getSize());
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.getSize());
        try {
            storage.get(UUID_1);
        } catch (NotExistStorageException e) {
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteGetNotExistStorageException() throws Exception {
        storage.get("dummy");
    }
}