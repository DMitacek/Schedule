package com.utb.d_mitacek.schedule;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



import java.util.Calendar;



public class ScheduleFragmentAdd extends DialogFragment {
    private EditText mesto;
    private EditText nazev;
    private EditText cas;
    private EditText datum;
    private String datum1;

    interface IDialogAddData {
        void onDialogAddBtnClick(String fotka, String name, String city,String date, String time);
    }
    private IDialogAddData mCallback = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.schedule_add_action, container,false);
        Calendar currentTime = Calendar.getInstance();
        nazev = rootView.findViewById(R.id.editTextNazev);
        mesto = rootView.findViewById(R.id.editTextMesto);
        cas = rootView.findViewById(R.id.editTextCas);
        datum = rootView.findViewById(R.id.editTextDatum);
        datum.setText(currentTime.get(Calendar.DAY_OF_MONTH)+"."+currentTime.get(Calendar.MONTH)+1+"."+currentTime.get(Calendar.YEAR));

        String minute = Integer.toString(currentTime.get(Calendar.MINUTE));
        if(minute.length()==1){
            minute = "0".concat(minute);
        }

        cas.setText(currentTime.get(Calendar.HOUR_OF_DAY)+":"+ minute);

        rootView.findViewById(R.id.klikni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                String[] datum1 = datum.getText().toString().split("\\.");
                String[] cas1 = cas.getText().toString().split(":");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(datum1[2]));
                calendar.set(Calendar.MONTH, Integer.parseInt(datum1[1])-1);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datum1[0]));
                calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(cas1[0]));
                calendar.set(Calendar.MINUTE,Integer.parseInt(cas1[1]));*/

                mCallback.onDialogAddBtnClick("http://openweathermap.org/img/w/01d.png", nazev.getText().toString(), mesto.getText().toString(), datum.getText().toString(), cas.getText().toString());
                dismiss();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (IDialogAddData)getActivity();

        } catch (ClassCastException e) {
            Log.d("MyDialog", "Activity doesn't implement the IDialogAddData interface");
        }
    }



}
