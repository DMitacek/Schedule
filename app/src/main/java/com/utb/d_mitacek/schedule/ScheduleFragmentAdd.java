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


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;



public class ScheduleFragmentAdd extends DialogFragment {
    private EditText mesto;
    private EditText nazev;
    private EditText cas;
    private EditText datum;
    private String datum1;
    private ScheduleItem newItem;
    private RequestQueue queue;

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
        queue = Volley.newRequestQueue(this.getContext());
        String minute = Integer.toString(currentTime.get(Calendar.MINUTE));
        if(minute.length()==1){
            minute = "0".concat(minute);
        }

        cas.setText(currentTime.get(Calendar.HOUR_OF_DAY)+":"+ minute);

        rootView.findViewById(R.id.klikni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItem = new ScheduleItem(1,"", nazev.getText().toString(), mesto.getText().toString(), datum.getText().toString(), cas.getText().toString());
                find_weather(getContext(), newItem, queue);
                String l = newItem.getmImageResource();
                mCallback.onDialogAddBtnClick(newItem.getImageResource(), nazev.getText().toString(), mesto.getText().toString(), datum.getText().toString(), cas.getText().toString());
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

    public void find_weather(Context context, final ScheduleItem item1, RequestQueue queue){

        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+ item1.getCity() +",CZ&appid=fe47a7288fc4af31e584cfd1eb0f6fe5&units=metric";
        String url1;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ScheduleItem item = item1;
                    JSONArray list = response.getJSONArray("list");
                    //Calendar cal = Calendar.getInstance();
                    String[] date = item.getDate().split("\\.");
                    String date1 = date[2]+"-"+date[1]+"-"+date[0];
                    String[] time = item.getTime().split(":");
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
                                item1.setmImageResource("http://openweathermap.org/img/w/"+icon+".png");
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

        queue.add(jor);
    }

}
