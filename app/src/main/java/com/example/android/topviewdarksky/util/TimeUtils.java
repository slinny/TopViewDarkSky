package com.example.android.topviewdarksky.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TimeUtils {
    private static Map<Integer, String> mDays = new HashMap<>();

    static {
        mDays.put(2, "MON");
        mDays.put(3, "TUES");
        mDays.put(4, "WED");
        mDays.put(5, "THU");
        mDays.put(6, "FRI");
        mDays.put(7, "SAT");
        mDays.put(1, "SUN");
    }

    public static String getDayOfWeek(Integer dateInt) {
        Long nowTimeLong = new Long(dateInt).longValue() * 1000;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        try {
            Date now = format.parse(format.format(nowTimeLong));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            return mDays.get(calendar.get(Calendar.DAY_OF_WEEK));
        } catch (ParseException e) {

        }
        return mDays.get(Calendar.DAY_OF_WEEK);
    }
}
