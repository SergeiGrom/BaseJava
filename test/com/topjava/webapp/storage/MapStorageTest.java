package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MapStorageTest extends AbstractStorageTest{

    public MapStorageTest() {
        super(new <Resume>MapStorage());
    }

    @Test
    public void getAll() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] actual = storage.getAll();
        Arrays.sort(actual);
        Assert.assertArrayEquals(expected, actual);
    }
}