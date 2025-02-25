package com.topjava.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    //    private static final Object LOCK = new  Object();
    private final AtomicInteger atomicCounter = new AtomicInteger();
    private static final Lock lock = new ReentrantLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
//            Thread thread = new  Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });

//            thread.start();
//            threads.add(thread);
        }
//     threads.forEach(t->{
//         try {
//             t.join();
//         } catch (InterruptedException e) {
//             throw new RuntimeException(e);
//         }
//     });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println("counter: " + mainConcurrency.atomicCounter.get());
    }

    private void inc() {
//        lock.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            lock.unlock();
//        }
    }

}
