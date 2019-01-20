package com.utb.d_mitacek.schedule;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

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
/*
    public ScheduleItem(int id, int imageResource, String text1, String text2) {
        ID = id;
        mImageResource = imageResource;
        name = text1;
        city = text2;
    }
*/

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

    public void find_weather(){
        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+ getCity() +",CZ&appid=fe47a7288fc4af31e584cfd1eb0f6fe5&units=metric";
        String url1;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray list = response.getJSONArray("list");
                    //Calendar cal = Calendar.getInstance();
                    String[] date = getDate().split("\\.");
                    String date1 = date[2]+"-"+date[1]+"-"+date[0];
                    String[] time = getTime().split(":");
                    int i;

                    for(i=0; i<list.length(); i++){
                        JSONObject weather = list.getJSONObject(i);
                        String[] cdate =weather.getString("dt_txt").split(" ");
                        if(date1.equals(cdate[0])){
                            String[] apitime = cdate[1].split(":");
                            int cas = Integer.parseInt(apitime[0])+3 - Integer.parseInt(time[0]);
                            if (cas < 3 && cas > 0) {

                                JSONArray array= weather.getJSONArray("weather");
                                String icon = array.getJSONObject(0).getString("icon");
                                mImageResource=  "http://openweathermap.org/img/w/"+icon+".png";

                            }
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

    }
}
