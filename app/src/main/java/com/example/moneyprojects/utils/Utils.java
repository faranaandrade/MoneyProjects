package com.example.moneyprojects.utils;

public class Utils {
    public static Long purgeTime(Long date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(date);
        purgeCalendar(calendar);
        return calendar.getTimeInMillis();
    }

    private static void purgeCalendar(java.util.Calendar calendar) {
        calendar.set(java.util.Calendar.MILLISECOND,0);
        calendar.set(java.util.Calendar.SECOND,0);
        calendar.set(java.util.Calendar.MINUTE,0);
        calendar.set(java.util.Calendar.HOUR,0);
    }

    public static java.util.Calendar getPurgeCalendar() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        purgeCalendar(calendar);
        return calendar;
    }
}
