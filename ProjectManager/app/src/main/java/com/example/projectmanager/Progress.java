package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Progress extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openresultactivity();
            }
        }));
    }

    public void openresultactivity() {
        Intent intent = new Intent(this, Result.class);
        startActivity(intent);
    }
}