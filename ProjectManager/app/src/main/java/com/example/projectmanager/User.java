package com.example.projectmanager;

public class User {
    private String usermail;

    public User(){
    }

    public User(String pMail){
        usermail = pMail;
    }

    public String getUsermail(){
        return usermail;
    }

    public void setUsermail(String pMail){
        usermail = pMail;
    }

}
