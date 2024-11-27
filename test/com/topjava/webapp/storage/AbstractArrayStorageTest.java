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

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(AbstractArrayStorage.RESUME_1);
        storage.save(AbstractArrayStorage.RESUME_2);
        storage.save(AbstractArrayStorage.RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(AbstractArrayStorage.RESUME_1.getUuid());
        storage.update(resume);
        Assert.assertSame(storage.get(AbstractArrayStorage.RESUME_1.getUuid()), resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() throws Exception {
        assertGet(AbstractArrayStorage.RESUME_1);
        assertGet(AbstractArrayStorage.RESUME_2);
        assertGet(AbstractArrayStorage.RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        try {
            storage.get(AbstractArrayStorage.RESUME_1.getUuid());
            storage.get(AbstractArrayStorage.RESUME_2.getUuid());
            storage.get(AbstractArrayStorage.RESUME_3.getUuid());
        } catch (NotExistStorageException e) {
        }
        assertSize(0);
        Assert.assertArrayEquals(new Storage[0], storage.getAll());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expected = new Resume[]{AbstractArrayStorage.RESUME_1, AbstractArrayStorage.RESUME_2, AbstractArrayStorage.RESUME_3};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test(expected = StorageException.class)
    public final void saveTestOverflow() throws Exception {
        try {
            for (int i = storage.getSize(); i < AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Overflow exception occurred before storage overflow");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveGetExistStorageException() throws Exception {
        storage.save(AbstractArrayStorage.RESUME_1);
    }

    @Test
    public void save() throws Exception {
        storage.save(AbstractArrayStorage.RESUME_4);
        assertSize(4);
        assertGet(AbstractArrayStorage.RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(AbstractArrayStorage.RESUME_1.getUuid());
        assertSize(2);
        storage.get(AbstractArrayStorage.RESUME_1.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteGetNotExistStorageException() throws Exception {
        storage.delete("dummy");
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.getSize());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}