package com.conference.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.conference.scheduler.SESSION_TYPE.AFTERNOON;
import static com.conference.scheduler.SESSION_TYPE.MORNING;

public class ConferenceScheduler {
    public Conference schedule(ArrayList<Talk> talks) {

        Conference.ConferenceBuilder conferenceBuilder = new Conference.ConferenceBuilder()
                .title("Conference ##");

        Stack<Talk> talkStack = sortAndConvertToStack(talks);
        List<Track> tracks = new ArrayList<>();
        Track.TrackBuilder trackBuilder = new Track.TrackBuilder();
        Session morningSession = new Session(MORNING);
        Session afternoonSession = new Session(AFTERNOON);
        int talkIterator = 1;

        while (!talkStack.empty()) {
            Talk currentTalk = talkStack.pop();

            if ((morningSession.fitsIntoSession(currentTalk) || afternoonSession.fitsIntoSession(currentTalk))) {
                if (morningSession.fitsIntoSession(currentTalk)) {
                    morningSession.insertTalk(currentTalk);
                } else if (afternoonSession.fitsIntoSession(currentTalk)) {
                    afternoonSession.insertTalk(currentTalk);
                }
            } else {
                trackBuilder.morningSession(morningSession);
                trackBuilder.afternoonSession(afternoonSession);
                trackBuilder.title(String.format("Track %d", talkIterator));
                tracks.add(trackBuilder.build());

                morningSession = new Session(MORNING);
                afternoonSession = new Session(AFTERNOON);
                trackBuilder = new Track.TrackBuilder();
                talkIterator++;
            }

        }
        conferenceBuilder.tracks(tracks);
        return conferenceBuilder.build();
    }

    private Stack<Talk> sortAndConvertToStack(ArrayList<Talk> talks) {
        talks.sort(
                (t1, t2) -> {
                    if (t1.getDuration() == t2.getDuration()) {
                        return 0;
                    } else {
                        return (t1.getDuration() < t2.getDuration()) ? -1 : 1;
                    }
                }
        );

        Stack<Talk> stack = new Stack();
        talks.forEach(stack::push);
        return stack;
    }

}
