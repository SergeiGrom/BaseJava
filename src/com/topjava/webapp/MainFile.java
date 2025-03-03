package com.topjava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {
        File filePath = new File(".\\.gitignore");
        try {
            System.out.println(filePath.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("C:\\IdeaProjects\\BaseJava");
        try {
            System.out.println(dir.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream((filePath))) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        // Lesson_8 HW task Recursion
        System.out.println("\nRecursion");
        System.out.println(dir);
        prtDirList(dir, "");
    }

    private static void prtDirList(File dir, String separator) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(separator + "├── " + file.getName());
                if (file.isDirectory()) {
                    prtDirList(file, separator + "|   ");
                }
            }
        }
    }
}


