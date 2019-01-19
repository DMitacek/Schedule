package com.utb.d_mitacek.schedule;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class ScheduleItem {
    private int mImageResource;
    private int ID;
    private String name;
    private String city;
    private String date;
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ScheduleItem() {

    }
/*
    public ScheduleItem(int id, int imageResource, String text1, String text2) {
        ID = id;
        mImageResource = imageResource;
        name = text1;
        city = text2;
    }
*/

    public ScheduleItem(int ID, int mImageResource, String name, String city, String date, String time) {
        this.mImageResource = mImageResource;
        this.ID = ID;
        this.name = name;
        this.city = city;
        this.date = date;
        this.time = time;
    }


    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return name;
    }

    public String getText2() {
        return city;
    }
}
