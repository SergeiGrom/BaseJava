package com.topjava.webapp;

public class MainDeadlockExample {
    private static final Object FIRST_OBJ = new Object();
    private static final Object SECOND_OBJ = new Object();

    public static void main(String[] args) {
        deadLock(FIRST_OBJ, SECOND_OBJ);
        deadLock(SECOND_OBJ, FIRST_OBJ);
    }

    private static void deadLock(Object obj_1, Object obj_2) {
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " - waiting obj_1");
            synchronized (obj_1) {
                System.out.println(threadName + " - lock obj_1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(threadName + " - waiting obj_2");
                synchronized (obj_2) {
                    System.out.println(threadName + " - lock obj_2");
                }
            }
        }).start();
    }
}








