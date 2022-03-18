package com.faaya.moneyprojects.utils;

import java.util.Calendar;

public class Utils {

    private static void purgeCalendar(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
    }

    public static Calendar getPurgeCalendar() {
        Calendar calendar = Calendar.getInstance();
        purgeCalendar(calendar);
        return calendar;
    }
}
