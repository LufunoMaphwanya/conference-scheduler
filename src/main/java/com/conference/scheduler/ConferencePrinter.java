package com.conference.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConferencePrinter {
    public static void prettyPrint(Conference scheduledConference) {
        scheduledConference.getTracks().forEach(ConferencePrinter::prettyPrint
        );
    }

    private static void prettyPrint(Track track) {
        System.out.println("\n" + track.getTitle() + ":\n");

        track.getMorningSession().talks.forEach(System.out::println
        );

        System.out.println("         ------   :-) Lunch ----   ");

        track.getAfternoonSession().talks.forEach(System.out::println
        );
    }

    public static void prettyPrintWithTimes(Conference scheduledConference) throws ParseException {

        for (Track track : scheduledConference.getTracks()) {
            System.out.println("\n\n" + track.getTitle() + ":\n");

            String startTime = "09:00";

            for (Talk talk : track.getMorningSession().talks) {

                System.out.println(startTime + " :" + talk.toString());
                startTime = addTimeOnto(startTime, talk.getDuration());

            }

            startTime = "12:00";
            System.out.println("         ------   :-) Lunch ----   ");

            for (Talk talk : track.getAfternoonSession().talks) {

                System.out.println(startTime + ":" + talk.toString());
                startTime = addTimeOnto(startTime, talk.getDuration());

            }


        }
    }


    private static String addTimeOnto(String time, int minutesToAddon) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = df.parse(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, minutesToAddon);
        return df.format(cal.getTime());
    }
}
