package com.example.projectmanager;

public class User {
    private String usermail;
    private String userid;
    private String username;

    public User(){
    }

    public User(String pMail, String pId, String uName){
        usermail = pMail;
        userid = pId;
        username = uName;
    }

    public String getUsermail(){
        return usermail;
    }

    public void setUsermail(String pMail){
        usermail = pMail;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String pName){
        username = pName;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String pId){
        userid = pId;
    }

}

