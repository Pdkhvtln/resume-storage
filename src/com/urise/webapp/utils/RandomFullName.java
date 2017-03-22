package com.urise.webapp.utils;

import java.util.Random;

/**
 * Created by Andrey on 17.01.2017.
 */
public class RandomFullName {
    public static String gen() {
        StringBuilder result = new StringBuilder();
        int lengthFullName;
        for (; ; ) {
            lengthFullName = (int) (1 + Math.random() * 15);
            if (lengthFullName > 5 && lengthFullName < 15)
                break;
        }
        for (int i = 0; i < lengthFullName; i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            result.append(c);
        }
        return result.toString();
    }
}
