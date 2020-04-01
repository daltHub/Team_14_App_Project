package com.example.projectmanager;

public class Groupuser {
    private String userid;
    private String groupid;
    private String groupname;

    public Groupuser(){
    }

    public Groupuser(String gId, String uId, String gName){
        userid = uId;
        groupid = gId;
        groupname = gName;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String pId){
        userid = pId;
    }

    public String getGroupname(){
        return groupname;
    }

    public void setGroupname(String gName){
        groupname = gName;
    }

    public String getGroupid(){
        return groupid;
    }

    public void setGroupid(String pId){
        groupid = pId;
    }

}
