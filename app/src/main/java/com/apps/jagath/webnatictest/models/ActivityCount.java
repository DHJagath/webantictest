package com.apps.jagath.webnatictest.models;

/**
 * Created by Jagath on 9/28/17.
 */

public class ActivityCount {
    private String date;
    private String hour;
    private int count;

    public ActivityCount(String date, String hour, int count) {
        this.date = date;
        this.hour = hour;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
