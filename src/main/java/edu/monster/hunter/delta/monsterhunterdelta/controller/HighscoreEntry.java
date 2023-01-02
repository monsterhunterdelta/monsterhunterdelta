package edu.monster.hunter.delta.monsterhunterdelta.controller;

import edu.monster.hunter.delta.monsterhunterdelta.persistence.HighscoreImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HighscoreEntry {

    private final SimpleStringProperty name;
    private final SimpleIntegerProperty score;

    public HighscoreEntry(String name, int score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);

    }

    public String getName() {

        return name.get();
    }

    // TODO Call HighScoreImpl class after being created
    public void save() {
        HighscoreImpl highscore = new HighscoreImpl(this.name.get(), this.score.get());
        highscore.saveHighscoreToFile();
    }


    public int getScore() {

        return score.get();
    }
}
