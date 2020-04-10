package com.example.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class meeting_setter extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Button confirm, settime;
    CalendarView calendarView;
    String day, mth, yr, min, hour;
    int intday, intmth, intyr, intmin, inthour;
    String wholedata;
    Context context = this;

    String groupID;
    final meeting_helper set = new meeting_helper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_setter);

        groupID = getIntent().getStringExtra("GRPIDVOTE");
        set.setGroupID(groupID);
        calendarView = (CalendarView) findViewById(R.id.calendarViewmeeting_setter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                yr = String.valueOf(year);
//                mth = String.valueOf(month+1);
//                day = String.valueOf(dayOfMonth);

                intday = dayOfMonth;
                intmth = month+1;
                intyr = year;

                set.setYear(year);
                set.setMonth(month+1);
                set.setDate(dayOfMonth);
            }
        });

        confirm = (Button) findViewById(R.id.buttonconfirmsetting);
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String formatyr, formatmth, formatday, formathour, formatmin;

                formatmth = String.format("%02d", intmth);
                formatday = String.format("%02d", intday);
                formathour = String.format("%02d", inthour);
                formatmin = String.format("%02d", intmin);

                wholedata = yr + "/" + formatmth + "/" + formatday + "/" + formathour + "/" + formatmin;

                if(yr != null){
                    set.setStringoftime(wholedata);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("meetings");
                    databaseReference.push().setValue(set);
                    //databaseReference.push().setValue("Success");
                    Intent newvote = new Intent(getBaseContext(), Display_meeting_avail.class);
                    newvote.putExtra("GROUPID", groupID);
                    startActivity(newvote);
                } else{
                    Toast.makeText(context, "Meeting not yet set, please retry", Toast.LENGTH_LONG).show();
                }


            }
        });

        Button setmoretime = (Button) findViewById(R.id.button7);
        setmoretime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String formatyr, formatmth, formatday, formathour, formatmin;

                formatmth = String.format("%02d", intmth);
                formatday = String.format("%02d", intday);
                formathour = String.format("%02d", inthour);
                formatmin = String.format("%02d", intmin);

                wholedata = yr + "/" + formatmth + "/" + formatday + "/" + formathour + "/" + formatmin;
                if(yr!=null){
                    set.setStringoftime(wholedata);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("meetings");
                    databaseReference.push().setValue(set);
                } else{
                    Toast.makeText(context, "Meeting not yet set, please retry", Toast.LENGTH_LONG).show();
                }


            }
        });

        settime = (Button) findViewById(R.id.Timesetter);
        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(yr != null){
                    DialogFragment timepicker = new displayresult();
                    timepicker.show(getSupportFragmentManager(), "MSG");
                } else{
                    Toast.makeText(context, "Date not yet set, please retry", Toast.LENGTH_LONG).show();
                }





            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_poll, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String groupId = getIntent().getStringExtra("GROUPID");
        Log.e("GROUPID - TEST", groupId);
        switch (item.getItemId()) {
            case R.id.action_homescreen:
                Intent goHome = new Intent(this, Homescreen.class);
                goHome.putExtra("GROUPID",groupId);
                startActivity(goHome);
                return true;
            case R.id.action_back_to_poll:
                Intent goPoll = new Intent(this, Voting_v2.class);
                goPoll.putExtra("GROUPID",groupId);
                startActivity(goPoll);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        TextView textview11 = (TextView) findViewById(R.id.textViewhour);
        TextView textview22 = (TextView) findViewById(R.id.textViewmin);
        textview11.setText("Hour: " + String.valueOf(hourOfDay));
        textview22.setText("Minute: " + String.valueOf(minute));
        hour = String.valueOf(hourOfDay);
        min = String.valueOf(minute);
        inthour = hourOfDay;
        intmin = minute;
        set.setHour(hourOfDay);
        set.setMinute(minute);


    }
}


