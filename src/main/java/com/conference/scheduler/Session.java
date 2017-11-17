package com.conference.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Session {

    List<Talk> talks;
    private String title;
    private int duration;
    private SESSION_TYPE sessionType;
    private int totalSessionSizeInMinutes;

    public Session(SESSION_TYPE session_type) {
        if ((session_type == SESSION_TYPE.MORNING)) {
            this.totalSessionSizeInMinutes = 180;
            this.duration = 180;
        } else {
            this.totalSessionSizeInMinutes = 230;
            this.duration = 230;
        }


        this.talks = new ArrayList<>();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SESSION_TYPE getSessionType() {
        return sessionType;
    }

    public void setSessionType(SESSION_TYPE sessionType) {
        this.sessionType = sessionType;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public int sumOfTalksCurrentlyInsession() {
        int sum = 0;

        for (Talk t : talks) {
            sum += t.getDuration();
        }

        return sum;

    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        for (Talk t : talks) {
            s.append(t.toString()).append("\n");
        }
        return s.toString();
    }

    public int availableSpace() {
        return this.totalSessionSizeInMinutes - this.sumOfTalksCurrentlyInsession();
    }

    public boolean fitsIntoSession(Talk t) {
        return availableSpace() > t.getDuration();
    }

    public void insertTalk(Talk currentTalk) {
        this.talks.add(currentTalk);
    }
}

