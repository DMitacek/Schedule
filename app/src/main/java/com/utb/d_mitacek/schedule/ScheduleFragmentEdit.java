package com.utb.d_mitacek.schedule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.Calendar;

public class ScheduleFragmentEdit extends DialogFragment {
    private EditText mesto;
    private EditText nazev;
    private EditText date;
    private EditText time;

    interface IDialogEditData {
        void onDialogEditBtnClick(int ID, String name, String city, String date, String time);
        void onDialogDeleteBtnClick(int ID);
    }
    private ScheduleFragmentEdit.IDialogEditData mCallback = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.schedule_edit_action, container,false);
        nazev = rootView.findViewById(R.id.editTextNazev);
        mesto = rootView.findViewById(R.id.editTextMesto);
        date = rootView.findViewById(R.id.editTextDatum);
        time = rootView.findViewById(R.id.editTextCas);
        Bundle bundle = this.getArguments();
        assert bundle != null;
        String bundleString = (String) bundle.get("item");
        final ScheduleItem item = new Gson().fromJson(bundleString,ScheduleItem.class);
        nazev.setText(item.getName());
        mesto.setText(item.getCity());
        date.setText(item.getDate());
        time.setText(item.getTime());



        rootView.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onDialogEditBtnClick(item.getID(), nazev.getText().toString(), mesto.getText().toString(),date.getText().toString(),time.getText().toString());
                dismiss();
            }
        });

        rootView.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onDialogDeleteBtnClick(item.getID());
                dismiss();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ScheduleFragmentEdit.IDialogEditData)getActivity();

        } catch (ClassCastException e) {
            Log.d("MyDialogEdit", "Activity doesn't implement the IDialogEditData interface");
        }
    }
}
