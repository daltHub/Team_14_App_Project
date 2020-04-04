package com.example.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Voting_v2 extends AppCompatActivity{

    ArrayList<State> listofstate = new ArrayList<State>();
    ListView listView;
    CustomAdapter adapter;
    Query query;
    private String TAG = "Voting_v2";
    Button settime;
    String groupId;


    private void mReadDataOnce(final OnGetDataListener listener){
        listener.onStart();

        final ArrayList<Integer> availList=new ArrayList();
        final ArrayList<String> keylist = new ArrayList<>();
        listView = (ListView) findViewById(R.id.Availability_list);


        query = FirebaseDatabase.getInstance().getReference("meetings").orderByChild("stringoftime");


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    listener.onSuccess(dataSnapshot);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        meeting_helper obj = snapshot.getValue(meeting_helper.class);
                        if (obj.getGroupID().equals(groupId)){
                            String nameofkey = snapshot.getKey();
                            int singlecount = obj.getCount();
                            String key = obj.getStringoftime();
                            availList.add(singlecount);
                            keylist.add(key);
                            State state = new State(obj, nameofkey);
                            listofstate.add(state);
                        }



                        //adapter.notifyDataSetChanged();
                    }
                    adapter = new CustomAdapter(Voting_v2.this, R.layout.row, listofstate);

                    listView.setAdapter(adapter);


                } else{
                    ArrayList<String> listItems = new ArrayList<String>();
                    listItems.add("No meetings yet");
                    ArrayAdapter ad = new ArrayAdapter(Voting_v2.this,
                            android.R.layout.simple_list_item_1, listItems);
                    listView.setAdapter(ad);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }


        });

        //listView.setAdapter(adapter1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_v2);

        groupId = getIntent().getStringExtra("GROUPIDVOTE");


        mReadDataOnce(new OnGetDataListener() {
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
        });

        Button newtime = (Button) findViewById(R.id.button5);

        newtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proceedtonewtime = new Intent(view.getContext(), meeting_setter.class);
                proceedtonewtime.putExtra("GRPIDVOTE", groupId);
                startActivity(proceedtonewtime);
            }
        });

    }

    public void showResult(View v) {

        for (State p : adapter.getStatelist()) {
            if (p.available){

                String date = p.getMeeting().getStringoftime();
                int newcount = p.getMeeting().getCount()+1;
                DatabaseReference dref = FirebaseDatabase.getInstance().getReference("meetings").child(p.getKey());
                meeting_helper oldcountmeeting = p.getMeeting();
                oldcountmeeting.setCount(newcount);
                dref.setValue(oldcountmeeting);


            }
        }
        //Toast.makeText(this, result+"\n"+"Total Amount:="+totalAmount, Toast.LENGTH_LONG).show();
    }

    public interface  OnGetDataListener{
        public void onStart();
        public void onSuccess(DataSnapshot data);
        public void onFailed(DatabaseError databaseError);
    }

}
