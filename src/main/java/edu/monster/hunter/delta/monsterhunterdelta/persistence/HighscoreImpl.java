package edu.monster.hunter.delta.monsterhunterdelta.persistence;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author quirin
 * TODO als Singleton implementieren
 */

public class HighscoreImpl implements Highscore {

    private final String name;
    private final Integer score;


    public HighscoreImpl(String name, int score) {
        this.name = name;
        this.score = score;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getScore() {
        return score;
    }


    @Override
    public void saveHighscoreToFile() {
        // write an array of high scores to file (serialization)
        ArrayList<Object> highscores = new ArrayList<Object>();
        try {
            FileInputStream fis = new FileInputStream("highscore3.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            highscores = (ArrayList<Object>) ois.readObject();

            ois.close();
        } catch (FileNotFoundException e) {
            try {
                highscores.add(this);

                FileOutputStream fos = new FileOutputStream("highscore3.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(highscores);
                oos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            highscores.add(this);
            FileOutputStream fos = new FileOutputStream("highscore3.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(highscores);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveHighScoreToServer() {
        // TODO Auto-generated method stub
        // So funktioniert die Kommunikation zum Server:
        // New User mit zwei Variablen (name und id) auf dem Server erstellen
        // User wird in die Datenbank geschrieben
        // String und int werden an den Server geschickt
        // Server schreibt in die Datenbank
        // Post request an den Server

        try {
            URL url = new URL("https://1672a66b-715e-4762-8d85-9f941b2c0ee7.mock.pstmn.io");
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("username", this.getName());
            params.put("highScore", this.score);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), StandardCharsets.UTF_8));
            }
            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

            for (int c; (c = in.read()) >= 0; )
                System.out.print((char) c);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String toString() {
        return "Highscore {" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }


}
