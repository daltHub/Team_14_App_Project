package com.example.projectmanager;

import java.util.ArrayList;

public class State {

    meeting_helper meeting;
    boolean available;
    String keyo;

    public State(meeting_helper meeting, String key) {
        this.meeting = meeting;
        available = false;
        keyo = key;
    }

    public String getKeyo() {
        return keyo;
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
