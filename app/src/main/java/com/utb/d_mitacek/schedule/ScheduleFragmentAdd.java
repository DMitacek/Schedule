package com.utb.d_mitacek.schedule;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class ScheduleFragmentAdd extends DialogFragment {
    private EditText mesto;
    private EditText nazev;
    interface IDialogEditData {
        void onDialogEditBtnClick(int fotka, String text1, String text2);
        void onDialogDeleteBtnClick(String text1);
        void onDialogAddBtnClick(int fotka, String text1, String text2);
    }
    private IDialogEditData mCallback = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.schedule_add_action, container,false);
        nazev = rootView.findViewById(R.id.editTextNazev);
        mesto = rootView.findViewById(R.id.editTextMesto);
        rootView.findViewById(R.id.klikni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onDialogAddBtnClick(R.drawable.ic_icons8_sun, nazev.getText().toString(), mesto.getText().toString());
                dismiss();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (IDialogEditData)getActivity();

        } catch (ClassCastException e) {
            Log.d("MyDialog", "Activity doesn't implement the IDialogAddData interface");
        }
    }
}
