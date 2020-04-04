package com.example.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class meeting_setter extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Button confirm, settime;
    CalendarView calendarView;
    String day, mth, yr, min, hour;
    int intday, intmth, intyr, intmin, inthour;
    String wholedata;

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
                mth = String.valueOf(month);
                day = String.valueOf(dayOfMonth);

                intday = dayOfMonth;
                intmth = month;
                intyr = year;

                set.setYear(year);
                set.setMonth(month+1);
                set.setDate(dayOfMonth);
            }
        });

        confirm = (Button) findViewById(R.id.button3);
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String formatyr, formatmth, formatday, formathour, formatmin;

                formatmth = String.format("%02d", intmth);
                formatday = String.format("%02d", intday);
                formathour = String.format("%02d", inthour);
                formatmin = String.format("%02d", intmin);

                wholedata = yr + "/" + formatmth + "/" + formatday + "/" + formathour + "/" + formatmin;
                set.setStringoftime(wholedata);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("meetings");
                databaseReference.push().setValue(set);
                //databaseReference.push().setValue("Success");
            }
        });

        settime = (Button) findViewById(R.id.Timesetter);
        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment timepicker = new displayresult();
                timepicker.show(getSupportFragmentManager(), "MSG");

            }
        });


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

