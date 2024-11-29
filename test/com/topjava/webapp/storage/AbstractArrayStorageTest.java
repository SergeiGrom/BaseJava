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
    private static final String UUID_NOT_EXIST = "UUID_NOT_EXIST";
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    //    introduce static block for education
    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

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
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(RESUME_1.getUuid());
        storage.update(resume);
        Assert.assertSame(storage.get(RESUME_1.getUuid()), resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(UUID_NOT_EXIST));
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
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
    public final void saveTestOverflow() throws Exception {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.CAPACITY; i++) {
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
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        storage.get(RESUME_1.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteGetNotExistStorageException() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}