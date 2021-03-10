package com.example.android.topviewdarksky;

import com.example.android.topviewdarksky.util.TimeUtils;

import org.junit.Assert;
import org.junit.Test;

public class TimeUtilsTest {

    @Test
    public void getDayofWeekCorrectly() {
        String day = "FRI";
        Assert.assertEquals(day, TimeUtils.getDayOfWeek("1585886400"));
    }
}