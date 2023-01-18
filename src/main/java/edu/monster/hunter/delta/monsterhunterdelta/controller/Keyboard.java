package edu.monster.hunter.delta.monsterhunterdelta.controller;

import edu.monster.hunter.delta.monsterhunterdelta.model.Direction;
import edu.monster.hunter.delta.monsterhunterdelta.view.PlayFieldScreen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableSet;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Keyboard {
    private final PlayFieldScreen playFieldScreen;

    public Keyboard(PlayFieldScreen playFieldScreen) {
        this.playFieldScreen = playFieldScreen;
    }

    public Timeline Timeline(ObservableSet<KeyCode> downKeys) {
        return new Timeline(new KeyFrame(
                Duration.millis(16), ae -> {
            downKeys.stream().parallel().forEach(kc -> {
                Platform.runLater(() -> {
                    switch (kc) {
                        case W:
                            if (playFieldScreen.getPlayer1().isMovable()) {
                                playFieldScreen.getPlayer1().setDirection(Direction.UP);
                                playFieldScreen.getPlayer1().move();
                                playFieldScreen.getPlayer1().getImageView().setImage(PlayFieldScreen.IMAGE_UP);
                            }
                            break;
                        case S:
                            if (playFieldScreen.getPlayer1().isMovable()) {
                                playFieldScreen.getPlayer1().setDirection(Direction.DOWN);
                                playFieldScreen.getPlayer1().move();
                                playFieldScreen.getPlayer1().getImageView().setImage(PlayFieldScreen.IMAGE_DOWN);
                            }
                            break;
                        case A:
                            if (playFieldScreen.getPlayer1().isMovable()) {
                                playFieldScreen.getPlayer1().setDirection(Direction.LEFT);
                                playFieldScreen.getPlayer1().move();
                                playFieldScreen.getPlayer1().getImageView().setImage(PlayFieldScreen.IMAGE_LEFT);
                            }
                            break;
                        case D:
                            if (playFieldScreen.getPlayer1().isMovable()) {
                                playFieldScreen.getPlayer1().setDirection(Direction.RIGHT);
                                playFieldScreen.getPlayer1().move();
                                playFieldScreen.getPlayer1().getImageView().setImage(PlayFieldScreen.IMAGE_RIGHT);
                            }
                            break;
                        case SPACE:
                            if (playFieldScreen.getPlayer1().isMovable()) {
                                playFieldScreen.getPlayer1().shoot();
                            }
                            break;
                        case UP:
                            if (playFieldScreen.getPlayer2().isMovable()) {
                                playFieldScreen.getPlayer2().setDirection(Direction.UP);
                                playFieldScreen.getPlayer2().move();
                                playFieldScreen.getPlayer2().getImageView().setImage(PlayFieldScreen.IMAGE_UP);
                            }
                            break;
                        case DOWN:
                            if (playFieldScreen.getPlayer2().isMovable()) {
                                playFieldScreen.getPlayer2().setDirection(Direction.DOWN);
                                playFieldScreen.getPlayer2().move();
                                playFieldScreen.getPlayer2().getImageView().setImage(PlayFieldScreen.IMAGE_DOWN);
                            }
                            break;
                        case LEFT:
                            if (playFieldScreen.getPlayer2().isMovable()) {
                                playFieldScreen.getPlayer2().setDirection(Direction.LEFT);
                                playFieldScreen.getPlayer2().move();
                                playFieldScreen.getPlayer2().getImageView().setImage(PlayFieldScreen.IMAGE_LEFT);
                            }
                            break;
                        case RIGHT:
                            if (playFieldScreen.getPlayer2().isMovable()) {
                                playFieldScreen.getPlayer2().setDirection(Direction.RIGHT);
                                playFieldScreen.getPlayer2().move();
                                playFieldScreen.getPlayer2().getImageView().setImage(PlayFieldScreen.IMAGE_RIGHT);
                            }
                            break;
                        case L:
                            if (playFieldScreen.getPlayer2().isMovable()) {
                                playFieldScreen.getPlayer2().shoot();
                            }
                            break;
                        case P:
                            playFieldScreen.pauseGame();
                            playFieldScreen.getPlayer1().toggleMoveable();
                            break;
                        case M:
                            playFieldScreen.muteMusic();
                        default:
                            break;

                    }
                });
            });
        }));
    }
}