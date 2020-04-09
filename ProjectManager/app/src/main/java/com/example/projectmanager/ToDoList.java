package com.example.projectmanager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ToDoList extends AppCompatActivity {
    private static final String TAG = "ToDoList";
    private ListView mTaskListView;
    private DatabaseReference mDatabase;
    private DatabaseReference taskref;
    Query taskquery;
    private ArrayAdapter<String> mAdapter;
    String groupId;
    List<ToDoTask> tasklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        groupId = getIntent().getStringExtra("GROUPID");
        mTaskListView = (ListView) findViewById(R.id.list_todo);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tasklist = new ArrayList<ToDoTask>();
        taskref = FirebaseDatabase.getInstance().getReference("tasks");
        taskquery = taskref.orderByChild("groupid").equalTo(groupId);
        taskquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> listItems = new ArrayList<String>();
                if (dataSnapshot.exists()) {
                    tasklist.clear();

                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                        ToDoTask todo = eventSnapshot.getValue(ToDoTask.class);
                        tasklist.add(todo);
                        listItems.add(todo.getTaskname());
                    }
                }
                else{
                    listItems.add("This group has no tasks yet");
                }
                ArrayAdapter ad = new ArrayAdapter(ToDoList.this,
                        android.R.layout.simple_list_item_1, listItems);
                mTaskListView.setAdapter(ad);
                mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        deleteTask(tasklist.get(position).getTaskid());
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_todo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_homescreen:
                Intent goHome = new Intent(this, Homescreen.class);
                startActivity(goHome);
                return true;
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                String id = mDatabase.child("tasks").push().getKey();
                                ToDoTask td = new ToDoTask(task,groupId,id);
                                mDatabase.child("tasks").child(id).setValue(td);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteTask(String taskId) {
        final String tas = taskId;
        final DatabaseReference tasksref = FirebaseDatabase.getInstance().getReference("tasks");
        AlertDialog.Builder newGroup = new AlertDialog.Builder(ToDoList.this);
        LinearLayout lila1= new LinearLayout(ToDoList.this);
        lila1.setOrientation(1); //1 is for vertical orientation
        newGroup.setView(lila1);
        newGroup.setCancelable(true)
                .setMessage("Remove this task?")
                .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tasksref.child(tas).removeValue();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = newGroup.create();
        alert.setTitle("Remove Task");
        alert.show();
    }


}
