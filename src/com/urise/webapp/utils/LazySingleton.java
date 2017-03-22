package com.urise.webapp.utils;

/**
 * Created by andrew on 09.03.17.
 */
public class LazySingleton {
    volatile private static LazySingleton INSTANCE;

    private static class LazySingletoHolder{
        private static final LazySingleton INSTANCE = new LazySingleton();
    }
    public static LazySingleton getInstance() {
        return LazySingletoHolder.INSTANCE;
    }

    //    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }

    private LazySingleton() {
    }


}
