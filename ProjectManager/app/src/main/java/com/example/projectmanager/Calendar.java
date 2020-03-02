package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


public class Calendar extends AppCompatActivity {

    private Button mButton;

    CalendarView calendarView;
    TextView dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        mButton = findViewById(R.id.button);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(final CalendarView calendarView, final int i, final int i1, final int i2) {

                dateDisplay.setText("Date: " + i2 + " / " + i1 + " / " + i);

                //Toast.makeText(getApplicationContext(), "Selected Date:\n" + "setDate: = " + calendarView.getDate(), Toast.LENGTH_LONG).show();



                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)

                    {
                        Intent intent;
                        final double v = (i * (1000 * 60 * 60 * 365.25 * 24)) + (i1 * (1000 * 60 * 60 * 720)) + (i2 * (86400000));
                        intent = new Intent(Intent.ACTION_INSERT)
                                .setType("vnd.android.cursor.item/event")
                                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY , true) // just included for completeness
                                .putExtra(CalendarContract.Events.TITLE, "My first project deadline")
                                .putExtra(CalendarContract.Events.DESCRIPTION, "Let's try to this on time...")
                                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Ireland")
                                .putExtra(CalendarContract.Events.ALL_DAY,true)
                                //.putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;COUNT=10")
                                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                                .putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE)
                                //.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, curDate[0])
                                //.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, setDate)
                                //.putExtra("beginTime", calendarView.getDate())
                                //.putExtra("beginTime", calendarView.getDate()-(1000*60*60*24*3))
                                .putExtra("beginTime", String.format("%s", v))
                                .putExtra(Intent.EXTRA_EMAIL, "yourComrad@example.com");

                        Toast.makeText(getApplicationContext(), String.format("Selected Date:\nsetMilliseconds: = %s", v), Toast.LENGTH_LONG).show();


                        //.putExtra(CalendarContract.)

                        startActivity(intent);
                        // Do something


                    }
                });

                Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }
        });



    }




}
