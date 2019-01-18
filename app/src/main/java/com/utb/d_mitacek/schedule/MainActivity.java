package com.utb.d_mitacek.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton actionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionButton = findViewById(R.id.floatingActionButton);
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

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new ScheduleFragmentAdd();
                dialogFragment.show(getSupportFragmentManager(), "dialogFragment");
            }
        });
    }
}
