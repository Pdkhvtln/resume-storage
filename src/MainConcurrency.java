import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by andrew on 08.03.17.
 */
public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private static final Object LOCK0 = new Object();
    private static final Object LOCK1 = new Object();
    private static final Lock lock = new ReentrantLock();
    private final AtomicInteger atomicInteger = new AtomicInteger();
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }

    };

    public static void main(String[] args) throws IOException, InterruptedException {
        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService completionService = new ExecutorCompletionService(executorService);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->
                    //            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
            System.out.println(future.isDone());
//            thread.start();
        }
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(counter);
    }

    private static void inc() {
//        double a = Math.sin(13.);

        //       synchronized (LOCK0) {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
//        }
    }


    public static void deadlock() {        //TODO на основе wait и notify сделать deadlock
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                synchronized (LOCK0) {
                    try {
                        //   Thread.sleep(10L);
                        System.out.println(Thread.currentThread().getName() + ", " + "LOCK0");
                    } catch (Throwable ignored) {
                    }
                    synchronized (LOCK1) {
                        try {
                            //         Thread.sleep(5L);
                            System.out.println(Thread.currentThread().getName() + ", " + "LOCK1");
                        } catch (Throwable ignored) {
                        }
                        LOCK1.notify();
                    }
                    LOCK0.notify();
                }
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (LOCK1) {
                    try {
                        //  Thread.sleep(10L);
                        System.out.println(Thread.currentThread().getName() + ", " + "LOCK1");
                    } catch (Throwable ignored) {
                    }
                    synchronized (LOCK0) {
                        try {
                            LOCK1.wait();
                            //    Thread.sleep(5L);
                            System.out.println(Thread.currentThread().getName() + ", " + "LOCK0");
                        } catch (Throwable ignored) {
                        }
                        LOCK0.notify();
                    }
                    LOCK1.notify();
                }
            }
        };

        thread0.start();
        thread1.start();
    }

    public static void exampleThread() {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        });
        thread0.start();
        System.out.println(thread0.getState());
    }

    public static void exampleListThread() {
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }
}
