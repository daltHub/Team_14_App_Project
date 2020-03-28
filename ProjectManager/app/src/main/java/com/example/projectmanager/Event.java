package com.example.projectmanager;

public class Event {
    private String Time;
    private String Date;
    private int GroupID;
    private String id;
    private String Name;
    private String Desc;



    public Event(){

    }

    public Event(String name, String Desc){
        this.Name = name;
        this.Desc = Desc;

    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDesc() {
        return Desc;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }
}

