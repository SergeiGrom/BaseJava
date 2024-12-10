package com.topjava.webapp;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.ListStorage;
import com.topjava.webapp.storage.MapResumeStorage;
import com.topjava.webapp.storage.MapUuidStorage;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "A");
    static final String UUID_2 = "uuid2";
    static  Resume RESUME_2 = new Resume(UUID_2, "B");
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "C");
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "A");

    public static void main(String[] args) {
        // Lesson BLOCK
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        collection.add(RESUME_4);

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (resume.getUuid().equals(UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection);

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        // bad option!
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        System.out.println();

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        for (Resume resume : collection) {
            System.out.println(resume);
        }
        // Lesson BLOCK ENDS.

        System.out.println("\nTEST BLOCK\n");

        System.out.println("LIST_STORAGE");
        ListStorage storage = new ListStorage();
        storage.save(RESUME_2);
        storage.save(RESUME_4);
        storage.save(RESUME_3);
        storage.save(RESUME_1);
        System.out.println("SIZE: " + storage.size());
        System.out.println(storage.getAllSorted());
//        RESUME_2 = new Resume(UUID_2, "NEW NAME");
        storage.update(RESUME_2);
        System.out.println(storage.getAllSorted());
        storage.delete(RESUME_1);
        System.out.println("SIZE: " + storage.size());
//        storage.update(RESUME_1);
        System.out.println(storage.getAllSorted());

        System.out.println("\nMAP_UUID_STORAGE");
        MapUuidStorage mapUuid = new MapUuidStorage();
        mapUuid.save(RESUME_1);
        mapUuid.save(RESUME_2);
        mapUuid.save(RESUME_3);
        mapUuid.save(RESUME_4);
        System.out.println("SIZE: " + mapUuid.size());
        System.out.println(mapUuid.getAllSorted());
//        RESUME_2 = new Resume(UUID_2, "NEW NAME");
        mapUuid.update(RESUME_2);
        System.out.println(mapUuid.getAllSorted());
        mapUuid.delete(RESUME_2);
        System.out.println("SIZE: " + mapUuid.size());
        System.out.println(mapUuid.getAllSorted());

        System.out.println("\nMAP_RESUME_STORAGE");
        MapResumeStorage mapResume = new MapResumeStorage();
        mapResume.save(RESUME_1);
        mapResume.save(RESUME_2);
        mapResume.save(RESUME_3);
        mapResume.save(RESUME_4);
        System.out.println("SIZE: " + mapResume.size());
        System.out.println(mapResume.getAllSorted());
        RESUME_2 = new Resume(UUID_2, "NEW NAME");
        mapResume.update(RESUME_2);
        System.out.println(mapResume.getAllSorted());
        mapResume.delete(RESUME_2);
        System.out.println("SIZE: " + mapResume.size());
        System.out.println(mapResume.getAllSorted());
    }
}
