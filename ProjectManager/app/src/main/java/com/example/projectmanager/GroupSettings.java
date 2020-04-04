package com.example.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    private DatabaseReference mDatabase;
    Query groupquery;
    Query memberquery;
    TextView groupName;
    List<Groupuser> groupuserList;
    ListView groupmembers;
    Button newMember;
    String groupId;
    String gName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);
        groupId = getIntent().getStringExtra("GROUPID");
        mDatabase = FirebaseDatabase.getInstance().getReference();;
        groupuserList = new ArrayList<Groupuser>();
        groupName = findViewById(R.id.groupName);
        groupmembers = findViewById(R.id.messages_view);
        newMember = findViewById(R.id.newMember);
        newMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder newGroup = new AlertDialog.Builder(GroupSettings.this);
                LinearLayout lila1= new LinearLayout(GroupSettings.this);
                lila1.setOrientation(1); //1 is for vertical orientation
                final EditText usermail = new EditText(GroupSettings.this);
                lila1.addView(usermail);
                newGroup.setView(lila1);
                newGroup.setCancelable(true)
                        .setMessage("Enter mail address of new member")
                        .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String value = usermail.getText().toString();
                                addNewGroupmember(value);
                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;
                AlertDialog alert = newGroup.create();
                alert.setTitle("Add new groupmember");
                alert.show();
            }
        });
        groupref = FirebaseDatabase.getInstance().getReference("groups");
        groupquery = groupref.orderByChild("groupid").equalTo(groupId);
        groupquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                        Group group = eventSnapshot.getValue(Group.class);
                        groupName.setText(group.getGroupname());
                        gName = group.getGroupname();
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
                    groupuserList.clear();
                    ArrayList<String> listItems = new ArrayList<String>();
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                        Groupuser groupuser = eventSnapshot.getValue(Groupuser.class);
                        groupuserList.add(groupuser);
                        listItems.add(groupuser.getUsername());
                    }
                    ArrayAdapter ad = new ArrayAdapter(GroupSettings.this,
                            android.R.layout.simple_list_item_1, listItems);
                    groupmembers.setAdapter(ad);
                    groupmembers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                            removeGroupmember(groupuserList.get(position).getEntryid(), groupuserList.get(position).getUsername());
                        }
                    });
                }
                else {
                    Log.e("MEMBERS", "Somehow this group has no members");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
                goHome.putExtra("GROUPID",groupId);
                startActivity(goHome);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void addNewGroupmember(String userMail){
        DatabaseReference userref = FirebaseDatabase.getInstance().getReference("users");
        Query userquery = userref.orderByChild("usermail").equalTo(userMail);
        userquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                        User newuser = eventSnapshot.getValue(User.class);
                        String id2 = mDatabase.child("groupuser").push().getKey();
                        Groupuser gu = new Groupuser(id2,groupId, newuser.getUserid(), gName, newuser.getUsername());
                        mDatabase.child("groupuser").child(id2).setValue(gu);
                    }
                }
                else {Log.e("TEST", "User doesn't exist!");}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    void removeGroupmember(String entryid, String username){
        final String ent = entryid;
        final DatabaseReference groupuserref = FirebaseDatabase.getInstance().getReference("groupuser");
        AlertDialog.Builder newGroup = new AlertDialog.Builder(GroupSettings.this);
        LinearLayout lila1= new LinearLayout(GroupSettings.this);
        lila1.setOrientation(1); //1 is for vertical orientation
        newGroup.setView(lila1);
        newGroup.setCancelable(true)
                .setMessage("Remove " + username + " from this group?")
                .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       groupuserref.child(ent).removeValue();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = newGroup.create();
        alert.setTitle("Remove groupmember");
        alert.show();
    }
}
