package com.topjava.webapp.storage;

import com.topjava.webapp.Config;
import com.topjava.webapp.ResumeTestData;
import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class AllStorageTest {
    public static final File STORAGE_DIR = Config.get().getStorageDir();
    Storage storage;
    static final Resume RESUME_1;
    static final Resume RESUME_2;
    static final Resume RESUME_3;
    static final Resume RESUME_4;
    static final String UUID_1 = String.valueOf(UUID.randomUUID());
    static final String UUID_2 = String.valueOf(UUID.randomUUID());
    static final String UUID_3 = String.valueOf(UUID.randomUUID());
    static final String UUID_4 = String.valueOf(UUID.randomUUID());

    public AllStorageTest(Storage storage) {
        this.storage = storage;
    }

    //    introduce static block for education
    static {
        RESUME_1 = ResumeTestData.fillResume(UUID_1, "A");
        RESUME_2 = ResumeTestData.fillResume(UUID_2, "B");
        RESUME_3 = ResumeTestData.fillResume(UUID_3, "C");
        RESUME_4 = ResumeTestData.fillResume(UUID_4, "A");
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_3);
        storage.save(RESUME_2);
        storage.save(RESUME_1);
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
        Assert.assertEquals(new ArrayList<>(0), storage.getAllSorted());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = ResumeTestData.fillResume(UUID_1, "A");
        storage.update(newResume);
        assertEquals(newResume, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_4);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(RESUME_4.getUuid());
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
        storage.delete(RESUME_4.getUuid());
    }

    @Test
    public void getAllSorted() throws Exception {
        storage.save(RESUME_4);
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3, RESUME_4);
        Collections.sort(expected);
        List<Resume> actual = storage.getAllSorted();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    protected void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    protected void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}