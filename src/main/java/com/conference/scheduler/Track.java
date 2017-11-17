package com.conference.scheduler;

public class Track {
    private String title;
    private Session morningSession;
    private Session afternoonSession;

    public Track(String title) {
        this.afternoonSession = new Session(SESSION_TYPE.AFTERNOON);
        this.morningSession = new Session(SESSION_TYPE.MORNING);
    }

    public Track(TrackBuilder builder) {
        this.morningSession = builder.morningSession;
        this.afternoonSession = builder.afternoonSession;
        this.title = builder.title;
    }

    public String getTitle() {
        return title;
    }

    public Session getMorningSession() {
        return morningSession;
    }

    public Session getAfternoonSession() {
        return afternoonSession;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("                               __________________________Morning session__________________________\n");
        s.append(morningSession.toString());
        s.append("                               _________________________Afternoon session__________________________\n");
        s.append(afternoonSession.toString());
        s.append("\n_______________________________________________________________________________________________\n");
        return s.toString();
    }

    public static class TrackBuilder {
        private String title;
        private Session morningSession;
        private Session afternoonSession;

        public TrackBuilder() {
        }

        public Track.TrackBuilder morningSession(Session session) {
            this.morningSession = session;
            return this;
        }


        public Track.TrackBuilder afternoonSession(Session session) {
            this.afternoonSession = session;
            return this;
        }

        public Track.TrackBuilder title(String title) {
            this.title = title;
            return this;
        }


        public Track build() {
            return new Track(this);
        }

    }

}
