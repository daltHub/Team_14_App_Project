package com.example.projectmanager;

public class ToDoTask {
    private String taskname;
    private String groupid;
    private String taskid;

    public ToDoTask(){

    }

    public ToDoTask(String tName, String gId, String tId){
        taskname = tName;
        groupid = gId;
        taskid = tId;
    }

    public String getTaskname(){return taskname;}
    public String getGroupid(){return groupid;}
    public String getTaskid(){return taskid;}
}
