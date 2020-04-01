package com.example.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupSettings extends AppCompatActivity {


    //private FirebaseAuth mAuth;
    private DatabaseReference memberref;
    private DatabaseReference groupref;
    Query groupquery;
    Query memberquery;
    TextView groupName;
    List<Groupuser> groupuserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);
        String groupId = getIntent().getStringExtra("GROUPID");
        groupuserList = new ArrayList<Groupuser>();
        groupName = findViewById(R.id.groupName);
        groupref = FirebaseDatabase.getInstance().getReference("groups");
        groupquery = groupref.orderByChild("groupid").equalTo(groupId);
        groupquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                        Group group = eventSnapshot.getValue(Group.class);
                        groupName.setText(group.getGroupname());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        memberref = FirebaseDatabase.getInstance().getReference("groupuser");
        memberquery = memberref.orderByChild("groupid").equalTo(groupId);
        memberquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                        Groupuser groupuser = eventSnapshot.getValue(Groupuser.class);
                        groupuserList.add(groupuser);
                        Log.e("MEMBERS","Adding member " +  groupuser.getUserid());
                    }
                }
                else {
                    Log.e("MEMBERS", "Somehow this group has no members");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Log.e("MEMBERS", String.valueOf(groupuserList.size()));


    }
}
