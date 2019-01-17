package com.utb.d_mitacek.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ScheduleItem> scheduleItems = new ArrayList<>();
        scheduleItems.add(new ScheduleItem(R.drawable.ic_icons8_sun,"hoho", "haha"));
        scheduleItems.add(new ScheduleItem(R.drawable.ic_icons8_sun,"hehe", "hihi"));
        scheduleItems.add(new ScheduleItem(R.drawable.ic_icons8_sun,"huhu", "huehue"));
    }
}
