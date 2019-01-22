package com.utb.d_mitacek.schedule;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements ScheduleFragmentAdd.IDialogAddData, ScheduleFragmentEdit.IDialogEditData, ScheduleAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ScheduleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton actionButton;
    private ArrayList<ScheduleItem> scheduleList = new ArrayList<>();
    private ScheduleItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButton = findViewById(R.id.floatingActionButton);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ScheduleAdapter(scheduleList);
        RequestQueue queue =Volley.newRequestQueue(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ScheduleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DialogFragment dialogFragment = new ScheduleFragmentEdit();
                Bundle bundle = new Bundle();
                bundle.putString("item",new Gson().toJson(mAdapter.getItem(position)));
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), "dialogFragment");
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new ScheduleFragmentAdd();
                dialogFragment.show(getSupportFragmentManager(), "dialogFragment");

            }
        });
    }



    @Override
    public void onDialogAddBtnClick(String fotka, String name, String city, String date, String time) {

        if(scheduleList.size()!= 0)
        {
            item = new ScheduleItem(scheduleList.get(scheduleList.size()-1).getID() + 1, fotka,name, city, date, time);
        }else
        {
            item = new ScheduleItem(1,fotka,name, city, date, time);
        }
        find_weather();


    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }

    @Override
    public void onDialogEditBtnClick(int ID, String name, String city, String date, String time) {

        for(int i = 0; i <= scheduleList.size()-1; i++)
        {
            if(scheduleList.get(i).getID() == ID)
            {
                scheduleList.get(i).setName(name);
                scheduleList.get(i).setCity(city);
                scheduleList.get(i).setDate(date);
                scheduleList.get(i).setTime(time);
                edit_weather(i);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogDeleteBtnClick(int ID) {
        for(int i = 0; i <= scheduleList.size()-1; i++)
        {
            if(scheduleList.get(i).getID() == ID)
            {
                scheduleList.remove(i);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public void find_weather(){

        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+ item.getCity() +",CZ&appid=fe47a7288fc4af31e584cfd1eb0f6fe5&units=metric";
        String url1;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray list = response.getJSONArray("list");
                    //Calendar cal = Calendar.getInstance();
                    String[] date = item.getDate().split("\\.");
                    String date1 = date[2]+"-"+date[1]+"-"+date[0];
                    String[] time = item.getTime().split(":");
                    int i;

                    for(i=0; i<list.length(); i++){
                        JSONObject weather = list.getJSONObject(i);
                        String[] cdate = weather.getString("dt_txt").split(" ");
                        if(date1.equals(cdate[0])){
                            String[] apitime = cdate[1].split(":");
                            int cas = Integer.parseInt(apitime[0]) - Integer.parseInt(time[0]);
                            if (cas < 3 && cas >= 0) {
                                JSONObject array1= weather.getJSONObject("main");
                                String[] temp = { array1.getString("temp"), array1.getString("temp_min"), array1.getString("temp_max")};
                                item.setTeplota(temp);
                                JSONArray array= weather.getJSONArray("weather");
                                String icon = array.getJSONObject(0).getString("icon");
                                item.setmImageResource("http://openweathermap.org/img/w/"+icon+".png");
                                scheduleList.add(item);
                                mAdapter.notifyDataSetChanged();
                                break;
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
        RequestQueue queue1 = Volley.newRequestQueue(this);
        queue1.add(jor);
    }

    public void edit_weather(final int e){

        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+ scheduleList.get(e).getCity() +",CZ&appid=fe47a7288fc4af31e584cfd1eb0f6fe5&units=metric";
        String url1;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray list = response.getJSONArray("list");
                    //Calendar cal = Calendar.getInstance();
                    String[] date = scheduleList.get(e).getDate().split("\\.");
                    String date1 = date[2]+"-"+date[1]+"-"+date[0];
                    String[] time = scheduleList.get(e).getTime().split(":");
                    int i;

                    for(i=0; i<list.length(); i++){
                        JSONObject weather = list.getJSONObject(i);
                        String[] cdate = weather.getString("dt_txt").split(" ");
                        if(date1.equals(cdate[0])){
                            String[] apitime = cdate[1].split(":");
                            int cas = Integer.parseInt(apitime[0]) - Integer.parseInt(time[0]);
                            if (cas < 3 && cas >= 0) {
                                JSONObject array1= weather.getJSONObject("main");
                                String[] temp = { array1.getString("temp"), array1.getString("temp_min"), array1.getString("temp_max")};
                                scheduleList.get(e).setTeplota(temp);
                                JSONArray array= weather.getJSONArray("weather");
                                String icon = array.getJSONObject(0).getString("icon");
                                scheduleList.get(e).setmImageResource("http://openweathermap.org/img/w/"+icon+".png");
                                mAdapter.notifyDataSetChanged();
                                break;
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
        RequestQueue queue1 = Volley.newRequestQueue(this);
        queue1.add(jor);
    }

}
