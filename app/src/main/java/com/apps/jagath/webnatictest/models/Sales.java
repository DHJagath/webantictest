package com.apps.jagath.webnatictest.models;

/**
 * Created by Jagath on 9/28/17.
 */

public class Sales {
    private int sale;
    private String month;
    private int year;

    public Sales(int sale, String month, int year) {
        this.sale = sale;
        this.month = month;
        this.year = year;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
