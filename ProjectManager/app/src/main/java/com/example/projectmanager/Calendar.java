package com.example.projectmanager;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Calendar extends AppCompatActivity implements EventPopUp.DialogListener {


    int d, m, y;
    String date;
    CalendarView calendarView;
    DatabaseReference reff;
    Event event;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Event> eventList;
    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);
        eventList = new ArrayList<>();
        calendarView = findViewById(R.id.calendarView);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(this, eventList);
        recyclerView.setAdapter(adapter);

        reff = FirebaseDatabase.getInstance().getReference("events");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(final CalendarView calendarView, int i, int i1, int i2) {
                i1 = i1 +1;

                //dateDisplay.setText("Date: " + i2 + " / " + i1  + " / " + i);
                d = i2;
                m = i1;
                y = i;
                date  = String.valueOf(d) + "/" + String.valueOf(m) + "/" + String.valueOf(y);
                //Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
                query = reff.orderByChild("date").equalTo(date);
                displayEvents();

            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_calendar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_homescreen:
                Intent goHome = new Intent(this, Homescreen.class);
                startActivity(goHome);
                return true;

            case R.id.action_add_event:
                Bundle args = new Bundle();
                args.putString("date", date);
                openDialog(args);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addEvent(String text, String time, String desc){
        event=new Event();
        //String text = inputField.getText().toString();
        String id = reff.push().getKey();
        event.setId(id);
        event.setName(text);
        event.setDesc(desc);
        event.setTime(time);
        event.setDate(date);
        reff.child(id).setValue(event);
        Toast.makeText(Calendar.this,"Event Saved",Toast.LENGTH_SHORT).show();
    }


    public void openDialog(Bundle bundle){
        EventPopUp eventPopUp = new EventPopUp();
        eventPopUp.setArguments(bundle);
        eventPopUp.show(getSupportFragmentManager(), "CreateEvent");
    }

    @Override
    public void applyText(String Name, String time, String desc){
        //inputField.setText(Name);
        addEvent(Name, time, desc);
    }

    protected void displayEvents(){
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                        Event event = eventSnapshot.getValue(Event.class);

                        eventList.add(event);
                    }
                }
                query = reff.orderByChild("time");
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



}
