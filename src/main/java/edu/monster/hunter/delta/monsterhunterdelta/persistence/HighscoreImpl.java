package edu.monster.hunter.delta.monsterhunterdelta.persistence;

import java.io.*;
import java.util.ArrayList;


/**
 * @author basti
 */
public class HighscoreImpl implements Highscore {

    private final String name;
    private final Integer score;

    private String highscore = "";

    public HighscoreImpl(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static save(ArrayList<Object> scores) {
        FileInputStream fis = new FileInputStream("highscore.txt");

        ArrayList<Object> objects = new ArrayList<Object>();

        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                Object loadedObj = null;
                while ((loadedObj = ois.readObject()) != null) {
                    objects.add(loadedObj);
                }

                return objects;
            } finally {
                ois.close();
            }

        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Object> load(String filename) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("highscore.txt");

        ArrayList<Object> objects = new ArrayList<Object>();

        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                Object loadedObj = null;
                while ((loadedObj = ois.readObject()) != null) {
                    objects.add(loadedObj);
                }

                return objects;
            } finally {
                ois.close();
            }

        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveHighscore(String name, int score) {

        highscore = name + ": " + score;


    }

    @Override
    public void saveHighscoreToFile() {
        this.saveHighscore(this.name, this.score);
        StringBuffer highscoreForSaving = new StringBuffer(this.loadHighscore());
        try {
            FileOutputStream fos = new FileOutputStream("highscore.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(highscoreForSaving);
            oos.close();


        } catch (IOException ioException) {
            System.out.println();
        }

    }

    @Override
    public String loadHighscoreFromFile() {
        StringBuffer highscoreForLoading = null;
        try {
            FileInputStream fis = new FileInputStream("highscore.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            highscoreForLoading = (StringBuffer) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println(ioException);
        }
        System.out.println(highscoreForLoading);
        return String.valueOf(highscoreForLoading);
    }

    @Override
    public String loadHighscore() {

        return highscore;
    }

}
