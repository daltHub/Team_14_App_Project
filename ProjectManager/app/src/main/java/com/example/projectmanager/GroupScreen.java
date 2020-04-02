package com.example.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference groupuserref;
    Button addNewGroup;
    Query groupidquery;
    List<Groupuser> groupuserList;
    ListView groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_screen);
        groupList = findViewById(R.id.messages_view);
        groupuserList = new ArrayList<Groupuser>();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        groupuserref = FirebaseDatabase.getInstance().getReference("groupuser");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        groupidquery = groupuserref.orderByChild("userid").equalTo(uid);

        groupidquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                groupuserList.clear();
                if (dataSnapshot.exists()) {

                    ArrayList<String> listItems = new ArrayList<String>();
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                        Groupuser groupuser = eventSnapshot.getValue(Groupuser.class);

                        groupuserList.add(groupuser);
                        listItems.add(groupuser.getGroupname());
                    }
                    ArrayAdapter ad = new ArrayAdapter(GroupScreen.this,
                            android.R.layout.simple_list_item_1, listItems);
                    groupList.setAdapter(ad);
                    groupList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                            String groupId = groupuserList.get(position).getGroupid();
                            Intent homes = new Intent(getBaseContext(), Homescreen.class);
                            homes.putExtra("GROUPID",groupId);
                            startActivity(homes);
                        }
                    });
                }
                else {
                    ArrayList<String> listItems = new ArrayList<String>();
                    listItems.add("You are not in any groups");
                    ArrayAdapter ad = new ArrayAdapter(GroupScreen.this,
                            android.R.layout.simple_list_item_1, listItems);
                    groupList.setAdapter(ad);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        addNewGroup =  findViewById(R.id.addNewGroup);
        addNewGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder newGroup = new AlertDialog.Builder(GroupScreen.this);
                LinearLayout lila1= new LinearLayout(GroupScreen.this);
                lila1.setOrientation(1); //1 is for vertical orientation
                final EditText name = new EditText(GroupScreen.this);
                lila1.addView(name);
                newGroup.setView(lila1);
                newGroup.setCancelable(true)
                        .setMessage("Enter Group Name")
                        .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String value = name.getText().toString();
                                addNewGroup(value);
                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;
                AlertDialog alert = newGroup.create();
                alert.setTitle("Create new group");
                alert.show();
            }
        });
    }

    void addNewGroup(String groupName){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = mDatabase.child("groups").push().getKey();
        String id2 = mDatabase.child("groupuser").push().getKey();
        String uid = user.getUid();
        String uname = user.getDisplayName();
        Group g = new Group(groupName, id);
        mDatabase.child("groups").child(id).setValue(g);
        Groupuser gu = new Groupuser(id2,id,uid,groupName,uname);
        mDatabase.child("groupuser").child(id2).setValue(gu);
        Intent groupSet = new Intent(getBaseContext(), GroupSettings.class);
        groupSet.putExtra("GROUPID",id);
        startActivity(groupSet);
    }
}
