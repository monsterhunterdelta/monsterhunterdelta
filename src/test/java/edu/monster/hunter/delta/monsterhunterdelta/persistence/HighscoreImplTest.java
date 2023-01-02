package edu.monster.hunter.delta.monsterhunterdelta.persistence;

import edu.monster.hunter.delta.monsterhunterdelta.controller.HighscoreEntry;
import edu.monster.hunter.delta.monsterhunterdelta.controller.HighscoreEntryList;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

class HighscoreImplTest {

    @Test
    void saveHighscore() {
    }

    @Test
    void saveHighscoreToFile() {
        HighscoreImpl highscore = new HighscoreImpl("test2", 1);
        highscore.saveHighscoreToFile();


    }

    @Test
    void saveHighsoreToServer() {
        HighscoreImpl highscore = new HighscoreImpl("test2", 1);
        highscore.saveHighScoreToServer();
    }

    @Test
    void loadHighscoresFromFile() {
        HighscoreEntryList highscoreEntryList = new HighscoreEntryList(new HighscoreEntry[50]);
        highscoreEntryList.loadHighscores();
        // print "here"
        System.out.println(highscoreEntryList.loadHighscoreFromFile().toString());
        System.out.println("here");
        System.out.println(highscoreEntryList.getHighscoreEntryList()[0].getName());
    }

    @Test
    void loadHighscoreFromServer() throws MalformedURLException {
        HighscoreEntryList highscoreEntryList = new HighscoreEntryList(new HighscoreEntry[50]);
        highscoreEntryList.loadHighscores();

    }

}