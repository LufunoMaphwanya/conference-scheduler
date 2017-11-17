package com.conference.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Conference {

    private String title;
    private int duration;
    private List<Track> tracks;

    private Conference(ConferenceBuilder builder) {
        this.title = builder.title;
        this.duration = builder.duration;
        this.tracks = builder.tracks;
    }

    private Conference() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks() {
        this.tracks = tracks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {

        StringBuilder tracksConcat = new StringBuilder();

        for (Track t : this.tracks) {
            tracksConcat.append(t.toString());
        }

        return "***********************************************************************\n"
                + "*******************        CONFERENCE     ****************************\n"
                + "***********************************************************************\n\n"
                + tracksConcat.toString()
                + "\n\n***********************************************************************\n";
    }

    public static class ConferenceBuilder {
        private String title;
        private int duration;
        private List<Track> tracks;

        public ConferenceBuilder() {
        }

        public ConferenceBuilder tracks(List<Track> tracks) {
            this.tracks = tracks;
            return this;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ConferenceBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ConferenceBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Conference build() {
            return new Conference(this);
        }

    }
}
