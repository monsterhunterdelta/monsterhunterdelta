package edu.monster.hunter.delta.monsterhunterdelta.controller;


import edu.monster.hunter.delta.monsterhunterdelta.persistence.HighscoreImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;


// create a class that is a list of HighscoreEntrys
public class HighscoreEntryList {
    // create a list of HighscoreEntrys
    private HighscoreEntry[] highscoreEntryList;

    // create a constructor that takes a list of HighscoreEntrys
    public HighscoreEntryList(HighscoreEntry[] highscoreEntryList) {
        this.highscoreEntryList = highscoreEntryList;
    }

    static ArrayList<Object> sortHighscores(ArrayList<Object> highscores) {
        highscores.sort(new Comparator<Object>() {

            public int compare(Object o1, Object o2) {
                return ((HighscoreImpl) o1).getScore().compareTo(((HighscoreImpl) o2).getScore());
            }
        });

        // highscores.sort((o1, o2) -> ((HighscoreImpl) o1).getScore().compareTo(((HighscoreImpl) o2).getScore()));
        //             highscores.sort(Comparator.comparing(HighscoreImpl::getScore));
        return highscores;
    }

    // create a getter for the list of HighscoreEntrys
    public HighscoreEntry[] getHighscoreEntryList() {
        return highscoreEntryList;
    }

    // create a setter for the list of HighscoreEntrys
    public void setHighscoreEntryList(HighscoreEntry[] highscoreEntryList) {
        this.highscoreEntryList = highscoreEntryList;
    }

    // add to the top of this list
    public void loadHighscores() {
        // load the highscores from the file
        // add them to the list
        // ArrayList<Object> highscores = loadHighscoreFromFile();
        //

        ArrayList<Object> highscores = loadHighScoresFromServer();


        // for every highscore in the list create a new highscoreEntry
        for (int i = 0; i < highscores.size(); i++) {
            HighscoreImpl highscore = (HighscoreImpl) highscores.get(i);
            HighscoreEntry highscoreEntry = new HighscoreEntry(highscore.getName(), highscore.getScore());
            highscoreEntryList[i] = highscoreEntry;
        }


    }

    public ArrayList<Object> loadHighscoreFromFile() {
        // read an array of HighscoreImpl from file (deserialization)


        try {
            FileInputStream fis = new FileInputStream("highscore3.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Object> highscores = (ArrayList<Object>) ois.readObject();
            for (Object highscore : highscores) {
                System.out.println(highscore);
            }
            ois.close();


            // Sort highscores
            sortHighscores(highscores);
            System.out.println("Sorted highscores: " + highscores);

            // set highscoreEntryList to the sorted highscores


            return highscores;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Object> loadHighScoresFromServer() {
        // TODO Auto-generated method stub
        // so funktioniert die Kommunikation zum Server:
        // Get request an den Server
        // Da kommt ein sortiertes Json-Object zurück
        // Dieses Json-Object sieht so aus:
        // Scores= [ {name: "Basti", score: 100}, {name: "Basti", score: 100}, {name: "Basti", score: 100}
        // Json-Object wird in ein Java-Object umgewandelt
        // Java-Object wird in eine Liste von HighscoreImpl umgewandelt
        ArrayList<Object> highscores = new ArrayList<>();

        try {
            // get request to server
            String url = "https://d703b332-7f63-4846-b95f-1e3a0a7e7fd7.mock.pstmn.io";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print in String
            System.out.println(response);
            String stringy = response.toString();

            // convert String to JSONObject
            JSONParser parser = new JSONParser();

            JSONArray jsonObject = (JSONArray) parser.parse(stringy);

            // Object test = parser.parse(new FileReader(System.getProperty("user.dir") + "/src/main/resources/edu/monster/hunter/delta/monsterhunterdelta/server.json"));
            // JSONArray jsonObject = (JSONArray) test;

            //Read JSON response and print
            System.out.println("result after Reading JSON Response");
            System.out.println("origin- " + jsonObject);

            // for every element in json array create a new highscoreImpl and add it to the list of highscores
            for (int i = 0; i < jsonObject.size(); i++) {
                JSONObject value = (JSONObject) jsonObject.get(i);

                long l = (long) value.get("highScore");
                int inty = (int) l;
                HighscoreImpl highscore = new HighscoreImpl((String) value.get("username"), inty);
                highscores.add(highscore);
            }
            System.out.println("Highscores: " + highscores);
            return highscores;

        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
        // get response from server
        // convert response to Java-Object
        // convert Java-Object to List of HighscoreImpl
        // sort List of HighscoreImpl
        // return List of HighscoreImpl


    }


}