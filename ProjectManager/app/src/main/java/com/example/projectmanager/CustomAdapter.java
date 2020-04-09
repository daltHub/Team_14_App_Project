package com.example.projectmanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<State> {

    private Context mcontext;
    private ArrayList<State> statelist;
    customButtonListener customListner;


    public ArrayList<State> getStatelist() {
        return statelist;
    }

    public void setStatelist(ArrayList<State> statelist) {
        this.statelist = statelist;
    }

    public interface customButtonListener {
        public void onButtonClickListner(int position,String value);
    }

    public CustomAdapter(Context voting_v2, int row, ArrayList<State> states) {
        super(voting_v2, row, states);
        this.mcontext = voting_v2;
        this.statelist = states;
//        this.list = keylist;
//        this.valuelist = value;

    }

    public void setcustomadapter(customButtonListener listener){
        this.customListner = listener;
    }

    public class ViewHolder {
        TextView date;
        TextView count;
        Button button;
    }

    public State getStatelist(int position) {
        return getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, null);
        }

        State singlestate = getItem(position);

        TextView tv1 = (TextView) v.findViewById(R.id.textViewfirstcol);
        TextView tv2 = (TextView) v.findViewById(R.id.textViewsecol);
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBoxavail);

        checkBox.setOnCheckedChangeListener(myCheckChangList);
        checkBox.setTag(position);
        checkBox.setChecked(singlestate.available);

        tv1.setText(singlestate.getMeeting().getStringoftime());
        tv2.setText(String.valueOf(singlestate.getMeeting().getCount()));

        return v;

    }

    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            getItem((Integer) compoundButton.getTag()).available = isChecked;
        }
    };

    @Override
    public int getCount() {
        return statelist.size();
    }


    @Nullable
    @Override
    public State getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
