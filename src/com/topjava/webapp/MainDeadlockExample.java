package com.topjava.webapp;

public class MainDeadlockExample {
    private static final Object FIRST_OBJ = new Object();
    private static final Object SECOND_OBJ = new Object();

    public static void main(String[] args) {

        class Deadlock implements Runnable{
            @Override
            public void run() {
                doWrite();
                doRead();
            }
        }

        Deadlock process = new Deadlock();
        Thread thread_1 = new Thread(process, "FIRST thread");
        Thread thread_2 = new Thread(process, "SECOND thread");
        thread_1.start();
        thread_2.start();
    }

    public static void doWrite() {
        synchronized (FIRST_OBJ) {
            System.out.println(Thread.currentThread().getName()
                               + " - lock FIRST_OBJ");
            synchronized (SECOND_OBJ) {
                System.out.println(Thread.currentThread().getName()
                                   + " - lock SECOND_OBJ");
                System.out.println(Thread.currentThread().getName()
                                   + " - process write");
            }
        }
    }

    public static void doRead() {
        synchronized (SECOND_OBJ) {
            System.out.println(Thread.currentThread().getName()
                               + " - lock SECOND_OBJ");
            synchronized (FIRST_OBJ) {
                System.out.println(Thread.currentThread().getName()
                                   + " - lock FIRST_OBJ");
                System.out.println(Thread.currentThread().getName()
                                   + " - process read");
            }
        }
    }


}
