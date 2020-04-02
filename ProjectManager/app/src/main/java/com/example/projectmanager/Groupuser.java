package com.example.projectmanager;

public class Groupuser {
    private String userid;
    private String groupid;
    private String entryid;
    private String groupname;
    private String username;

    public Groupuser(){
    }

    public Groupuser( String eId, String gId, String uId, String gName, String uName){
        entryid = eId;
        userid = uId;
        groupid = gId;
        groupname = gName;
        username = uName;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String pId){
        userid = pId;
    }

    public String getEntryid(){
        return entryid;
    }

    public void setEntryid(String pId){
        entryid = pId;
    }

    public String getGroupname(){
        return groupname;
    }

    public void setGroupname(String gName){
        groupname = gName;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String uName){
        username = uName;
    }

    public String getGroupid(){
        return groupid;
    }

    public void setGroupid(String pId){
        groupid = pId;
    }

}
