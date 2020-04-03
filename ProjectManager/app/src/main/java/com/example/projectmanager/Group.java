package com.example.projectmanager;

import java.util.ArrayList;

public class Group {
    private String groupname;
    private String groupid;

    private ArrayList<meeting_helper> meeting;

    public Group(){
    }

    public Group(String pName, String pId){
        groupname = pName;
        groupid = pId;
    }

    public ArrayList<meeting_helper> getMeeting() {
        return meeting;
    }

    public void setMeeting(ArrayList<meeting_helper> meeting) {
        this.meeting = meeting;
    }

    public String getGroupname(){
        return groupname;
    }

    public void setGroupname(String pName){
        groupname = pName;
    }

    public String getGroupid(){
        return groupid;
    }

    public void setGroupid(String pId){
        groupid = pId;
    }

}
