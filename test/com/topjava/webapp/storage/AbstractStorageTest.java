package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractStorageTest {
    Storage storage;
    static final String UUID_NOT_EXIST = "UUID_NOT_EXIST";
    static final Resume RESUME_1;
    static final Resume RESUME_2;
    static final Resume RESUME_3;
    static final Resume RESUME_4;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    //    introduce static block for education
    static {
        RESUME_1 = new Resume("uuid1");
        RESUME_2 = new Resume("uuid2");
        RESUME_3 = new Resume("uuid3");
        RESUME_4 = new Resume("uuid4");
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveGetExistStorageException() throws Exception {
        storage.save(RESUME_1);
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

    protected void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    protected void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}