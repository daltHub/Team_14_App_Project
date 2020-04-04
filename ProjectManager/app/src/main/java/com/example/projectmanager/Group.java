package com.example.projectmanager;

public class Group {
    private String groupname;
    private String groupid;

    public Group(){
    }

    public Group(String pName, String pId){
        groupname = pName;
        groupid = pId;
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
