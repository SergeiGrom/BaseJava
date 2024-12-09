package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MapUuidStorageTest extends AbstractStorageTest{

    public MapUuidStorageTest() {
        super(new <Resume>MapUuidStorage());
    }

    @Test
    public void getAll() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] actual = storage.getAllSorted().toArray(new Resume[0]);
        Arrays.sort(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Override
    public void update() throws Exception {
        super.update();
    }
}