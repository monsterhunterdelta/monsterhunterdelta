package edu.monster.hunter.delta.monsterhunterdelta.persistence;

import java.io.*;

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
