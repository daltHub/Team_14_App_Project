package com.example.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Display_meeting_avail extends AppCompatActivity {

    ArrayList<State> listofstate = new ArrayList<State>();
    ListView listView;
    Context context = this;
    Meeting_display_adapter adapter;
    Query query;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_meeting_avail);
        Voting_v2 voteloading = new Voting_v2();

        Intent intent = this.getIntent();

        final String id = getIntent().getStringExtra("GROUPID");
        readfordisplay(new Voting_v2.OnGetDataListener() {
            @Override
            public void onStart() {
                Log.d("Start", "Starting...");
            }

            @Override
            public void onSuccess(DataSnapshot data) {

                Log.d("Sucess", "moving");
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        }, id);

        final DatabaseReference dref = FirebaseDatabase.getInstance().getReference("meetings");
        Button enter = (Button) findViewById(R.id.enterdelete);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText stringofdate = (EditText) findViewById(R.id.editText2);
                final String stringdate = stringofdate.getText().toString();

                //Toast.makeText(context, "Delete Success, Please return to HomePage", Toast.LENGTH_LONG).show();
                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(context, "Delete Success, Please return to HomePage", Toast.LENGTH_LONG).show();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                meeting_helper object = snapshot.getValue(meeting_helper.class);
                                if (object.getStringoftime().equals(stringdate) && object.getGroupID().equals(id)) {
                                    snapshot.getRef().removeValue();
                                    Toast.makeText(context, "Delete Success, Please return to HomePage", Toast.LENGTH_LONG).show();
                                    return;
                                }

                            }
                            Toast.makeText(context, "No such date! Enter again", Toast.LENGTH_LONG).show();
//                            return;

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

    }



    private void readfordisplay(final Voting_v2.OnGetDataListener listener, final String id){
        listener.onStart();

        listView = (ListView) findViewById(R.id.listtoshow);
        query = FirebaseDatabase.getInstance().getReference("meetings").orderByChild("stringoftime");


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    listener.onSuccess(dataSnapshot);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        meeting_helper obj = snapshot.getValue(meeting_helper.class);
                        if (obj.getGroupID().equals(id)) {
                            String nameofkey = snapshot.getKey();
                            State state = new State(obj, nameofkey);
                            listofstate.add(state);
                        }

                    }
                    adapter = new Meeting_display_adapter(context, R.layout.row_display, listofstate);
                    listView.setAdapter(adapter);
                }

                else{
                    ArrayList<String> listItems = new ArrayList<String>();
                    listItems.add("No meetings yet");
                    ArrayAdapter ad = new ArrayAdapter(context,
                            android.R.layout.simple_list_item_1, listItems);
                    listView.setAdapter(ad);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });
    }
}
