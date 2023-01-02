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


/* This a class is used to create a list of HighscoreEntrys
 * by loading the data from a file or a server.
 *
 */

public class HighscoreEntryList {
    // create a list of HighscoreEntrys
    private HighscoreEntry[] highscoreEntryList;

    // create a constructor that takes a list of HighscoreEntrys
    public HighscoreEntryList(HighscoreEntry[] highscoreEntryList) {
        this.highscoreEntryList = highscoreEntryList;
    }

    /*
     * This methods sorts an array of HighscoreEntrys by their score.
     */
    static ArrayList<Object> sortHighscores(ArrayList<Object> highscores) {
        highscores.sort(new Comparator<Object>() {

            public int compare(Object o1, Object o2) {
                return ((HighscoreImpl) o1).getScore().compareTo(((HighscoreImpl) o2).getScore());
            }
        });
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


        ArrayList<Object> highscores = loadHighScoresFromServer();


        // for every highscore in the list create a new highscoreEntry
        for (int i = 0; i < highscores.size(); i++) {
            HighscoreImpl highscore = (HighscoreImpl) highscores.get(i);
            HighscoreEntry highscoreEntry = new HighscoreEntry(highscore.getName(), highscore.getScore());
            highscoreEntryList[i] = highscoreEntry;
        }


    }

    // read an array of HighscoreImpl from file (deserialization)
    public ArrayList<Object> loadHighscoreFromFile() {


        try {
            FileInputStream fis = new FileInputStream("highscore3.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Object> highscores = (ArrayList<Object>) ois.readObject();

            ois.close();


            // Sort highscores
            sortHighscores(highscores);

            return highscores;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* This method loads the highscores from a server.
     * It uses the HTTP protocol to send a GET request to the server.
     * The server then sends a JSON file with the highscores.
     * The method then parses the JSON file and creates a list of HighscoreImpl.
     * The list is then returned.
     */
    public ArrayList<Object> loadHighScoresFromServer() {
        ArrayList<Object> highscores = new ArrayList<>();
        try {
            // get request to server
            String url = "https://d703b332-7f63-4846-b95f-1e3a0a7e7fd7.mock.pstmn.io";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            // add request header
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


            String stringy = response.toString();

            // convert String to JSONObject
            JSONParser parser = new JSONParser();
            JSONArray jsonObject = (JSONArray) parser.parse(stringy);


            // for every element in json array create a new highscoreImpl and add it to the list of highscores
            for (int i = 0; i < jsonObject.size(); i++) {
                JSONObject value = (JSONObject) jsonObject.get(i);

                long l = (long) value.get("highScore");
                int inty = (int) l;
                HighscoreImpl highscore = new HighscoreImpl((String) value.get("username"), inty);
                highscores.add(highscore);
            }
            return highscores;

        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }


    }


}