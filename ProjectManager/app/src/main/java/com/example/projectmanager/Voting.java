package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
//
//import com.firebase.client.Firebase;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Voting extends AppCompatActivity {

    private Button button1;
//    private Firebase mRef;

    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date, date1, date2, date3, date4;
    private SimpleDateFormat dateFormat1;
//
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseUser mFirebaseUser;
    private String mUsername;

//    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

//        Firebase.setAndroidContext(this);


        dateTimeDisplay = (CheckBox)findViewById(R.id.checkBox);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM dd yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date.concat(" 14:00"));


        final CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox2.setText(date.concat(" 15:00"));

        final CheckBox checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox3.setText(date.concat(" 16:00"));



        calendar.add(Calendar.DATE,1);

        date1 = dateFormat.format(calendar.getTime());
        final CheckBox checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        checkBox4.setText(date1.concat(" 14:00"));

        final CheckBox checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
        checkBox5.setText(date1.concat(" 15:00"));

        final CheckBox checkBox6 = (CheckBox)findViewById(R.id.checkBox6);
        checkBox6.setText(date1.concat(" 16:00"));


        calendar.add(Calendar.DATE,1);

        date2 = dateFormat.format(calendar.getTime());
        final CheckBox checkBox7 = (CheckBox)findViewById(R.id.checkBox7);
        checkBox7.setText(date2.concat(" 14:00"));

        final CheckBox checkBox8 = (CheckBox)findViewById(R.id.checkBox8);
        checkBox8.setText(date2.concat(" 15:00"));

        final CheckBox checkBox9 = (CheckBox)findViewById(R.id.checkBox9);
        checkBox9.setText(date2.concat(" 16:00"));


        calendar.add(Calendar.DATE,1);

        date3 = dateFormat.format(calendar.getTime());
        final CheckBox checkBox10 = (CheckBox)findViewById(R.id.checkBox10);
        checkBox10.setText(date3.concat(" 14:00"));

        final CheckBox checkBox11 = (CheckBox)findViewById(R.id.checkBox11);
        checkBox11.setText(date3.concat(" 15:00"));

        final CheckBox checkBox12 = (CheckBox)findViewById(R.id.checkBox12);
        checkBox12.setText(date3.concat(" 16:00"));


        calendar.add(Calendar.DATE,1);

        date4 = dateFormat.format(calendar.getTime());
        final CheckBox checkBox13 = (CheckBox)findViewById(R.id.checkBox13);
        checkBox13.setText(date4.concat(" 14:00"));

        final CheckBox checkBox14 = (CheckBox)findViewById(R.id.checkBox14);
        checkBox14.setText(date4.concat(" 15:00"));

        final CheckBox checkBox15 = (CheckBox)findViewById(R.id.checkBox15);
        checkBox15.setText(date4.concat(" 16:00"));

//        mRef = new Firebase("https://software-engineering-tcd-8.firebaseio.com/");
        button1 = (Button) findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                EditText text = (EditText) findViewById(R.id.editText);
                final String name = text.getText().toString();
//
//                List<Boolean> okieornot = new ArrayList<Boolean>();
//
//
//                okieornot.add(itemClicked(name, dateTimeDisplay));
//                okieornot.add(itemClicked(name, checkBox2));
//                okieornot.add(itemClicked(name, checkBox3));
//                okieornot.add(itemClicked(name, checkBox4));
//                okieornot.add(itemClicked(name, checkBox5));
//                okieornot.add(itemClicked(name, checkBox6));
//                okieornot.add(itemClicked(name, checkBox7));
//                okieornot.add(itemClicked(name, checkBox8));
//                okieornot.add(itemClicked(name, checkBox9));
//                okieornot.add(itemClicked(name, checkBox10));
//                okieornot.add(itemClicked(name, checkBox11));
//                okieornot.add(itemClicked(name, checkBox12));
//                okieornot.add(itemClicked(name, checkBox13));
//                okieornot.add(itemClicked(name, checkBox14));
//                okieornot.add(itemClicked(name, checkBox15));
//
//                Users user = new Users(name,okieornot);
//
//                writeobject(name, user);


                Intent intent = new Intent(getBaseContext(), Result.class);
                intent.putExtra("NAME_VARIABLE", name);

                intent.putExtra("Date", date);
                intent.putExtra("Date1", date1);
                intent.putExtra("Date2", date2);
                intent.putExtra("Date3", date3);
                intent.putExtra("Date4", date4);

                startActivity(intent);
            }
        });


    }

//    private void writeobject(String name, Users user){
//        Firebase firefire = new Firebase("https://software-engineering-tcd-8.firebaseio.com/");
//        Firebase childref = firefire.child("User");
//        childref.child(name).setValue(user);
//    }




//    public boolean itemClicked(String name, View v) {
//        //code to check if this checkbox is checked!
//        CheckBox checkBox = (CheckBox)v;
//        String datestring = ((CheckBox) v).getText().toString();
//        if(checkBox.isChecked()){
//            //writeNewUser(name, datestring, true);
//            return true;
//        }
//        else{
//            //writeNewUser(name, datestring, false);
//            return false;
//        }
//    }

}
