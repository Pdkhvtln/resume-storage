package com.urise.webapp.utils;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by andrew on 08.02.17.
 */
public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

}
