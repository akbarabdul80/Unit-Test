package com.zero.unittest.utils;

import java.util.Calendar;
import java.util.Date;

public class DateExt {
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Integer getDiffDays(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

    public static Date getToday() {
        return Calendar.getInstance().getTime();
    }

    public static Date getDayAgo(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }
}
