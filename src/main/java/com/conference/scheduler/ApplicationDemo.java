package com.conference.scheduler;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ApplicationDemo {
    public static void main(String[] args) throws ParseException, IOException {

        Scanner in = new Scanner(System.in);

        //FIXME: Don't wanna type out random talks, rather generate and save in text file then read
        System.out.println("Enter number of talks to randomly generate eg: 36");
        int numberOfTalks = in.nextInt();
        ArrayList<Talk> proposedTalks = new ArrayList<>();
        createRandomTalkData(numberOfTalks);

        proposedTalks = parseFromFile();


        ConferenceScheduler scheduler = new ConferenceScheduler();




        Conference scheduledConference = scheduler.schedule(proposedTalks);

        ConferencePrinter.prettyPrint(scheduledConference);
        //ConferencePrinter.prettyPrintWithTimes(scheduledConference);


    }

    private static ArrayList<Talk> parseFromFile() throws IOException {
        ArrayList<Talk> talks = new ArrayList<>();

        File f = new File("inputFile.txt");

        BufferedReader b = new BufferedReader(new FileReader(f));

        String readLine = "";

        System.out.println("Reading file using Buffered Reader");

        while ((readLine = b.readLine()) != null) {
            String[] line = readLine.split(" ");

            String duration = line[line.length-2];
            duration = duration.replace("mins", "");
            Integer durationNumber = Integer.parseInt(duration);
            String text = readLine.replace(duration, "");

                talks.add(new Talk(text, durationNumber));

        }

        return talks;

    }

    public static void createRandomTalkData(int numberOfTalks) throws IOException {
        File fout = new File("inputFile.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < numberOfTalks; i++) {
            bw.write((new Talk("A randomly generated, probably boring talk",
                    new Random().nextInt((61)))).toString());
        }

        bw.close();
    }
}
