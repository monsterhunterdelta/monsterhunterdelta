package edu.monster.hunter.delta.monsterhunterdelta.persistence;

import java.io.Serializable;

/**
 * @author quirin
 * This is the interface for the Highscore. It gets and sets the
 * score of a player.
 */
public interface Highscore extends Serializable {
    /**
     * This method should save someone's highscore.
     *
     * @param name  The name of the player.
     * @param score The score of the player.
     */
    /**
     * This method should save someone's highscore to a file.
     */
    void saveHighscoreToFile();


    /**
     * This method should load the actual highscore from a file.
     *
     * @return a String with the current highscore.
     */

    Integer getScore();

    String getName();

    void saveHighScoreToServer();


}
