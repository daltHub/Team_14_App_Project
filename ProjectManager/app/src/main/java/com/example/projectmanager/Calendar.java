package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Calendar extends AppCompatActivity  {

    private Button mButton;
    int d, m, y;
    String events[] = new String[31];
    CalendarView calendarView;
    TextView dateDisplay;
    EditText inputField;
    Button displayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);



        mButton = findViewById(R.id.ebutton);
        displayButton = findViewById(R.id.dbutton);

        inputField = findViewById(R.id.editText3);


        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(final CalendarView calendarView, int i, int i1, int i2) {
                i1 = i1 +1;

                dateDisplay.setText("Date: " + i2 + " / " + i1  + " / " + i);
                d = i2;
                m = i1;
                y = i;

                //Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }

        });

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text = inputField.getText().toString();
                if(TextUtils.isEmpty(events[d])) {
                    events[d] = text;
                }else{
                    events[d] = events[d] + "\n" + text;
                }
                Toast.makeText(getApplicationContext(), "Events Saved", Toast.LENGTH_SHORT).show();

            }
        });


        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dtext= events[d];
                Toast.makeText(getApplicationContext(), "Events: " + dtext, Toast.LENGTH_SHORT).show();
            }
        });






    }




}
