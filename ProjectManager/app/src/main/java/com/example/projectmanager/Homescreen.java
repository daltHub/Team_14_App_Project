package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Homescreen extends AppCompatActivity {


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

        toDo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toDoList = new Intent(v.getContext(), ToDoList.class);
                startActivity(toDoList);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent calend = new Intent(v.getContext(), Calendar.class);
                startActivity(calend);
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent messenger = new Intent(v.getContext(), Messenger.class);
                startActivity(messenger);
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent prog = new Intent(v.getContext(), Voting_v2.class);
                prog.putExtra("GROUPIDVOTE", groupId);
                startActivity(prog);
            }
        });

    }
}
