package com.utb.d_mitacek.schedule;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionButton = findViewById(R.id.floatingActionButton);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ScheduleAdapter(scheduleList);

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
            scheduleList.add(new ScheduleItem(scheduleList.get(scheduleList.size()-1).getID() + 1, "http://openweathermap.org/img/w/01d.png",name, city, date, time));
        }else
        {
            scheduleList.add(new ScheduleItem(1,"http://openweathermap.org/img/w/01d.png",name, city, date, time));
        }

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

}
