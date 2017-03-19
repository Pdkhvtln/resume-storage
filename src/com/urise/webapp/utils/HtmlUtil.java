package com.urise.webapp.utils;

import com.urise.webapp.model.Organization;

/**
 * Created by andrew on 19.03.17.
 */
public class HtmlUtil {
    public static String formatDates(Organization.Position position)
    {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }
}
