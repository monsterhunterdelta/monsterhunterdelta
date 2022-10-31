package edu.monster.hunter.delta.monsterhunterdelta.persistence;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class HighscoreImplTest {

    @Test
    void saveHighscore() {
    }

    @Test
    void saveHighscoreToFile() {
        HighscoreImpl highscore = new HighscoreImpl("test", 1);
        highscore.saveHighscoreToFile();

    }

    @Test
    void loadHighscoreFromFile() {
        HighscoreImpl highscore = new HighscoreImpl("test", 1);
        highscore.loadHighscoreFromFile();

    }

    @Test
    void loadHighscore() {
        String expected = "test: 1";


        HighscoreImpl highscore = new HighscoreImpl("test", 1);
        highscore.saveHighscore("test", 1);
//        System.out.println(highscore.loadHighscore());
        Assert.assertEquals(expected, highscore.loadHighscore());
    }
}