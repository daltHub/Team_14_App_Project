package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class Homescreen extends AppCompatActivity {

    @Override
    protected void onRestart() {
        super.onRestart();

        String groupIdloop = getIntent().getStringExtra("Looping");
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        final String groupId = getIntent().getStringExtra("GROUPID");
        Log.e("GROUPID - TEST", groupId);
        Button toDo = findViewById(R.id.button);
        Button messages = findViewById(R.id.button2);
        Button calendar = findViewById(R.id.button3);
        Button progress = findViewById(R.id.button4);
        Button groupsettings = findViewById(R.id.button5);
        Button groupscreen = findViewById(R.id.button6);

        toDo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toDoList = new Intent(v.getContext(), ToDoList.class);
                toDoList.putExtra("GROUPID",groupId);
                startActivity(toDoList);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent calend = new Intent(v.getContext(), Calendar.class);
                calend.putExtra("GROUPID",groupId);
                startActivity(calend);
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent messenger = new Intent(v.getContext(), Messenger.class);
                messenger.putExtra("GROUPID",groupId);
                startActivity(messenger);
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent prog = new Intent(v.getContext(), Voting_v2.class);
                prog.putExtra("GROUPID",groupId);
                startActivity(prog);
            }
        });

        groupsettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent grpset = new Intent(v.getContext(), GroupSettings.class);
                grpset.putExtra("GROUPID",groupId);
                startActivity(grpset);
            }
        });

        groupscreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent grpscr = new Intent(v.getContext(), GroupScreen.class);
                grpscr.putExtra("GROUPID",groupId);
                startActivity(grpscr);
            }
        });
    }
}
