package com.topjava.webapp;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractArrayStorage;
import com.topjava.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.topjava.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        ARRAY_STORAGE.save(AbstractArrayStorage.RESUME_1);
        ARRAY_STORAGE.save(AbstractArrayStorage.RESUME_2);
        ARRAY_STORAGE.save(AbstractArrayStorage.RESUME_3);
        ARRAY_STORAGE.save(AbstractArrayStorage.RESUME_4);

        System.out.printf("Get r1: %s%n", ARRAY_STORAGE.get(AbstractArrayStorage.RESUME_1.getUuid()));
        System.out.printf("Get r2: %s%n", ARRAY_STORAGE.get(AbstractArrayStorage.RESUME_2.getUuid()));
        System.out.printf("Get r3: %s%n", ARRAY_STORAGE.get(AbstractArrayStorage.RESUME_3.getUuid()));
        System.out.printf("Get r4: %s%n", ARRAY_STORAGE.get(AbstractArrayStorage.RESUME_4.getUuid()));
        System.out.printf("Get dummy: %s%n%n", ARRAY_STORAGE.get("dummy"));
        System.out.printf("Size: %d%n%n", ARRAY_STORAGE.getSize());

        ARRAY_STORAGE.update(AbstractArrayStorage.RESUME_1);
        ARRAY_STORAGE.update(AbstractArrayStorage.RESUME_2);
        ARRAY_STORAGE.update(AbstractArrayStorage.RESUME_3);
        ARRAY_STORAGE.update(AbstractArrayStorage.RESUME_4);

        printAll();
        ARRAY_STORAGE.delete(AbstractArrayStorage.RESUME_4.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.getSize());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume resume : ARRAY_STORAGE.getAll()) {
            System.out.println(resume);
        }
    }
}
