package com.utb.d_mitacek.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;



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
    public void onDialogAddBtnClick(int fotka, String text1, String text2) {
        if(scheduleList.size()!= 0)
        {
            scheduleList.add(new ScheduleItem(scheduleList.get(scheduleList.size()-1).getID() + 1, R.drawable.ic_icons8_sun,text1, text2));
        }else
        {
            scheduleList.add(new ScheduleItem(1, R.drawable.ic_icons8_sun,text1, text2));
        }

    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }

    @Override
    public void onDialogEditBtnClick(int ID, String text1, String text2) {
        for(int i = 0; i <= scheduleList.size()-1; i++)
        {
            if(scheduleList.get(i).getID() == ID)
            {
                scheduleList.get(i).setmText1(text1);
                scheduleList.get(i).setmText2(text2);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogDeleteBtnClick(String text1) {
        for(int i = 0; i <= scheduleList.size()-1; i++)
        {
            if(scheduleList.get(i).getText1().equals(text1))
            {
                scheduleList.remove(i);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
