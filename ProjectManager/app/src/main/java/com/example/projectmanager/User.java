package com.example.projectmanager;

public class User {
    private String usermail;
    private String userid;

    public User(){
    }

    public User(String pMail, String pId){
        usermail = pMail;
        userid = pId;
    }

    public String getUsermail(){
        return usermail;
    }

    public void setUsermail(String pMail){
        usermail = pMail;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String pId){
        userid = pId;
    }

}

