package edu.monster.hunter.delta.monsterhunterdelta.controller;

import edu.monster.hunter.delta.monsterhunterdelta.model.persistence.HighscoreImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author quirin
 * This class represents a HighscoreEntry.
 * It has a name and a score. It is used to display the highscore.
 */
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

    public int getScore() {

        return score.get();
    }

    /**
     * This method saves someone's highscore.
     */
    public void save() {
        HighscoreImpl highscore = new HighscoreImpl(this.name.get(), this.score.get());
        highscore.saveHighScoreToServer();
    }


}
