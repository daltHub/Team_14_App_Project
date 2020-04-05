package com.example.projectmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Meeting_display_adapter extends ArrayAdapter<State> {

    private Context context;
    private ArrayList<State> statelist;


    public Meeting_display_adapter(Context activity, int row, ArrayList<State> states) {
        super(activity, row, states);
        this.context = activity;
        this.statelist = states;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row_display, null);
        }

        State singlestate = getItem(position);

        TextView tvdisplay = (TextView) v.findViewById(R.id.TVtime);
        TextView tv2display = (TextView) v.findViewById(R.id.TVcount);

        String mth = String.format("%02d", singlestate.getMeeting().getMonth());
        String day = String.format("%02d", singlestate.getMeeting().getDate());
        String hour = String.format("%02d", singlestate.getMeeting().getHour());
        String min = String.format("%02d", singlestate.getMeeting().getMinute());

        String displaytime = mth + "-" + day + "  " + hour + " : " + min;

        tvdisplay.setText(displaytime);
        tv2display.setText(String.valueOf(singlestate.getMeeting().getCount()));

        return v;

    }

    @Nullable
    @Override
    public State getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return statelist.size();
    }
}


