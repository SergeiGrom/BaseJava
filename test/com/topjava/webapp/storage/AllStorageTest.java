package com.topjava.webapp.storage;

import com.topjava.webapp.ResumeTestData;
import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AllStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\IdeaProjects\\BaseJava\\storage");
    Storage storage;
    static final Resume RESUME_1;
    static final Resume RESUME_2;
    static final Resume RESUME_3;
    static final Resume RESUME_4;

    public AllStorageTest(Storage storage) {
        this.storage = storage;
    }

    //    introduce static block for education
    static {
        RESUME_1 = ResumeTestData.fillResume("uuid1", "A");
        RESUME_2 = ResumeTestData.fillResume("uuid2", "B");
        RESUME_3 = ResumeTestData.fillResume("uuid3", "C");
        RESUME_4 = ResumeTestData.fillResume("uuid4", "A");
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
        Resume newResume = ResumeTestData.fillResume("uuid1", "A");
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
        Resume[] expected = new Resume[]{RESUME_1, RESUME_4, RESUME_2, RESUME_3,};
        List<Resume> actual = storage.getAllSorted();
        Assert.assertArrayEquals(expected, actual.toArray());
    }

    protected void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    protected void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}