package com.urise.webapp.utils;

import com.urise.webapp.model.Organization;

/**
 * Created by andrew on 19.03.17.
 */
public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }
}
