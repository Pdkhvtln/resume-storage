import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 08.03.17.
 */
public class MainConcurrency {
    //public static final int THREADS_NUMBER = 10000;
    //private static int counter;
    private static final Object LOCK0 = new Object();
    private static final Object LOCK1 = new Object();

    public static void main(String[] args) throws IOException {
        //TODO на основе wait и notify сделать deadlock
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
//        System.out.println(Thread.currentThread().getName());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
//            }
//        }).start();
//        System.out.println(thread0.getState());
//
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    inc();
//                }
//            });
//            thread.start();
//            threads.add(thread);
//        }
//
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(counter);
//    }
//
//    private static void inc() {
//        double a = Math.sin(13.);
//
//        synchronized (LOCK) {
//            counter++;
//        }

}
