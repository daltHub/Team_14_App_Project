package com.example.projectmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Calendar extends AppCompatActivity implements EventPopUp.DialogListener {

    private Button mButton;
    int d, m, y;
    CalendarView calendarView;
    TextView dateDisplay;
    EditText inputField;
    Button displayButton;
    DatabaseReference reff;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);



        mButton = findViewById(R.id.ebutton);
        displayButton = findViewById(R.id.dbutton);


        calendarView = findViewById(R.id.calendarView);
        dateDisplay = findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");

        reff = FirebaseDatabase.getInstance().getReference("events");


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
                openDialog();

            }
        });


        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewEvents();
            }
        });






    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_homescreen:
                Intent goHome = new Intent(this, Homescreen.class);
                startActivity(goHome);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addEvent(String text, String desc){
        event=new Event();
        //String text = inputField.getText().toString();
        String id = reff.push().getKey();
        event.setId(id);
        event.setName(text);
        event.setDesc(desc);
        event.setDay(d);
        event.setMonth(m);
        event.setYear(y);
        reff.child(id).setValue(event);
        Toast.makeText(Calendar.this,"Event Saved",Toast.LENGTH_SHORT).show();
    }

    private void viewEvents(){

    }

    public void openDialog(){
        EventPopUp eventPopUp = new EventPopUp();
        eventPopUp.show(getSupportFragmentManager(), "CreateEvent");
    }

    @Override
    public void applyText(String Name, String desc){
        //inputField.setText(Name);
        addEvent(Name, desc);
    }
}
