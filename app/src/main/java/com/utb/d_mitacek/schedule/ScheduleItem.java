package com.utb.d_mitacek.schedule;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class ScheduleItem {
    private String mImageResource;
    private int ID;
    private String name;
    private String city;
    private String date;
    private String time;

    public String[] getTeplota() {
        return teplota;
    }

    public void setTeplota(String[] teplota) {
        this.teplota = teplota;
    }

    private String[] teplota;

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

    public String getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(String mImageResource) {
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

    public ScheduleItem(int ID, String mImageResource, String name, String city, String date, String time) {
        this.mImageResource = mImageResource;
        this.ID = ID;
        this.name = name;
        this.city = city;
        this.date = date;
        this.time = time;
    }


    public String getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return name;
    }

    public String getText2() {
        return city;
    }


}
