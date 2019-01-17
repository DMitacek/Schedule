package com.utb.d_mitacek.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ScheduleItem> scheduleList = new ArrayList<>();
        scheduleList.add(new ScheduleItem(R.drawable.ic_icons8_sun,"hoho", "haha"));
        scheduleList.add(new ScheduleItem(R.drawable.ic_icons8_sun,"hehe", "hihi"));
        scheduleList.add(new ScheduleItem(R.drawable.ic_icons8_sun,"huhu", "huehue"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ScheduleAdapter(scheduleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
