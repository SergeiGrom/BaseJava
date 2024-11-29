package com.topjava.webapp;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.ArrayStorage;

/**
 * Test for your com.topjava.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();


    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r4);

        System.out.printf("Get r1: %s%n", ARRAY_STORAGE.get(r1.getUuid()));
        System.out.printf("Get r2: %s%n", ARRAY_STORAGE.get(r2.getUuid()));
        System.out.printf("Get r3: %s%n", ARRAY_STORAGE.get(r3.getUuid()));
        System.out.printf("Get r4: %s%n", ARRAY_STORAGE.get(r4.getUuid()));
        System.out.printf("Get dummy: %s%n%n", ARRAY_STORAGE.get("dummy"));
        System.out.printf("Size: %d%n%n", ARRAY_STORAGE.size());

        ARRAY_STORAGE.update(r1);
        ARRAY_STORAGE.update(r2);
        ARRAY_STORAGE.update(r3);
        ARRAY_STORAGE.update(r4);

        printAll();
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume resume : ARRAY_STORAGE.getAll()) {
            System.out.println(resume);
        }
    }
}
