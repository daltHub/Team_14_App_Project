package com.example.projectmanager;

public class User {
    private String usermail;
    private String userid;
    private String username;
    private String id;

    public User(){
    }

    public User(String pMail, String pId, String pName){
        usermail = pMail;
        userid = pId;
        username = pName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsermail(){
        return usermail;
    }
    public String getUserid(){
        return userid;
    }
    public String getUsername(){
        return username;
    }
    public void setUsermail(String pMail){
        usermail = pMail;
    }
    public void setUsername(String pName){
        username = pName;
    }
    public void setUserid(String pId){
        userid = pId;
    }
}
