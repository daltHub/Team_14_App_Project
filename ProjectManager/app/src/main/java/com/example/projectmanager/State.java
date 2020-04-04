package com.example.projectmanager;

import java.util.ArrayList;

public class State {

    meeting_helper meeting;
    boolean available;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public State(meeting_helper meeting, String nameofkey) {
        this.meeting = meeting;
        this.key = nameofkey;
        available = false;
    }

    public meeting_helper getMeeting() {
        return meeting;
    }

    public void setMeeting(meeting_helper meeting) {
        this.meeting = meeting;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
