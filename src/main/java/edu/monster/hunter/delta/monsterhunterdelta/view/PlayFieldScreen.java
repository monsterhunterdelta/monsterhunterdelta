package edu.monster.hunter.delta.monsterhunterdelta.view;

import edu.monster.hunter.delta.monsterhunterdelta.controller.HighscoreEntry;
import edu.monster.hunter.delta.monsterhunterdelta.controller.Keyboard;
import edu.monster.hunter.delta.monsterhunterdelta.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.*;


public class PlayFieldScreen extends Application {

    private final static int START_X_PLAYER_1 = 130;
    private final static int START_Y_PLAYER_1 = 510;
    private final static int START_X_PLAYER_2 = 760;
    private final static int START_Y_PLAYER_2 = 510;
    private static final int ONE_SECOND = 1000;
    private static final int FPS = 30;
    /**
     * Mit dieser Wahrscheinlichkeit wird ein mal pro Sekunde geschossen.
     */
    private static final double SHOOT_LIKELIHOOD = 0.7;
    private final Timeline timeline = new Timeline();
    private final List<Enemy> enemyList = new ArrayList<Enemy>();
    private final List<Bullet> bulletList = new ArrayList<Bullet>();
    private final Group root = new Group();
    private final Label pause = new Label("PAUSE");
    private boolean gameWasPaused = true;
    private HighscoreEntry entry;

    private Media music;
    private MediaPlayer mediaPlayer;
    private Maze maze;
    private Player player1;
    private Player player2;
    private Stage primaryStage;

    private Enemy createEnemy(final TypeOfFigure enemyType, final int x,
                              final int y) {
        Enemy enemy = new Enemy(maze, enemyType, x, y);
        enemy.addTargets(player1);
        enemy.addTargets(player2);
        enemy.setShootCallback(new ShootCallbackImpl());

        enemyList.add(enemy);
        return enemy;
    }

    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Monster Hunter Delta");
        primaryStage.setResizable(false);


        maze = new Maze("level1");
        player1 = new Player(maze, START_X_PLAYER_1, START_Y_PLAYER_1);
        player2 = new Player(maze, START_X_PLAYER_2, START_Y_PLAYER_2);
        player1.setShootCallback(new ShootCallbackImpl());
        player2.setShootCallback(new ShootCallbackImpl());

        final Enemy enemy1 = createEnemy(TypeOfFigure.BURWOR, 130, 130);
        final Enemy enemy2 = createEnemy(TypeOfFigure.GARWOR, 855, 510);
        final Enemy enemy3 = createEnemy(TypeOfFigure.THORWOR, 855, 130);

        player1.addTargets(enemy1, enemy2, enemy3, player2);
        player2.addTargets(enemy1, enemy2, enemy3, player1);

        Label score1 = new Label("Score: " + player1.getScore());

        Label lives1 = new Label("Leben: " + player1.getLives());
        lives1.setLayoutY(40);

        Label score2 = new Label("Score: " + player2.getScore());
        score2.setLayoutX(950);

        Label lives2 = new Label("Leben: " + player2.getLives());
        lives2.setLayoutY(40);
        lives2.setLayoutX(950);

        Keyboard keyboard = new Keyboard(player1, player2, this);

        root.getChildren().add(player1.getGroup());
        root.getChildren().add(player2.getGroup());
        root.getChildren().add(enemy1.getGroup());
        root.getChildren().add(enemy2.getGroup());
        root.getChildren().add(enemy3.getGroup());
        root.getChildren().addAll(maze.getWalls());
        root.getChildren().add(score1);
        root.getChildren().add(lives1);
        root.getChildren().add(score2);
        root.getChildren().add(lives2);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);


        EventHandler<ActionEvent> actionPerFrame = new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent t) {

                introSequence();

                if (checkThatPlayerIsStillAlive()) {

                    moveAllEnemies();

                    moveAllBullets();

                    score1.setText("Score: " + player1.getScore());
                    score2.setText("Score: " + player2.getScore());

                } else {


                    if (player1.getLives() == 0 || player2.getLives() == 0) {

                        enterHighscore();

                    } else {
                        timeline.pause();
                        player1.getRectangle().setX(START_X_PLAYER_1);
                        player1.getRectangle().setY(START_Y_PLAYER_1);
                        player1.getImageView().setX(START_X_PLAYER_1);
                        player1.getImageView().setY(START_Y_PLAYER_1);
                        player1.setLives(player1.getLives() - 1);
                        lives1.setText("Leben: " + player1.getLives());
                        player1.setInvincible(true);
                        player1.setAlive(true);
                        player2.getRectangle().setX(START_X_PLAYER_2);
                        player2.getRectangle().setY(START_Y_PLAYER_2);
                        player2.getImageView().setX(START_X_PLAYER_2);
                        player2.getImageView().setY(START_Y_PLAYER_2);
                        // TODO Hier liegt das Problem mit dem gemeinsamen Lebensabzug
                        // else müsste mit einem Else if prüfen welcher Spieler noch am Leben ist
                        player2.setLives(player2.getLives() - 1);
                        lives2.setText("Leben: " + player2.getLives());
                        player2.setInvincible(true);
                        player2.setAlive(true);
                        timeline.play();
                        Timer timer = new Timer();

                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    player1.setInvincible(false);
                                    player2.setInvincible(false);
                                });

                            }
                        }, 3000);

                    }

                }

            }

        };

        KeyFrame keyframe = new KeyFrame(Duration.millis(ONE_SECOND / FPS),
                actionPerFrame);
        timeline.getKeyFrames().add(keyframe);
        timeline.play();

        Scene scene = new Scene(root, 1024, 740);
        scene.getStylesheets().add(PlayFieldScreen.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/controls.css").toExternalForm());
        scene.setOnKeyPressed(keyboard);

        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent w) {
                timeline.stop();

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                System.exit(0);
            }
        });

    }

    // TODO das muss noch überarbeitet werden
    // es muss entweder oder gecheckt werden, wer noch am leben ist
    // damit kein gemeinsamer Lebensabzug stattfindet
    private boolean checkThatPlayerIsStillAlive() {
        if (!player1.isAlive() || !player2.isAlive()) {
            gameWasPaused = true;
            player1.toggleMoveable();
            player2.toggleMoveable();
            return false;
        }
        return true;
    }

    private void enterHighscore() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        timeline.stop();
        // TODO check which player died in the end and add his score to the highscore list
        int finalScore = player1.getScore();
        String playerName = "Spieler 1";
        Label playersPoints = new Label("Du hast " + finalScore + " Punkte!");
        playersPoints.setTextFill(Color.WHITESMOKE);
        Label enterHighscore = new Label("Trage deinen Namen ein! ");
        enterHighscore.setTextFill(Color.WHITESMOKE);
        TextField name = new TextField(playerName);
        Button ok = new Button("Ok");
        VBox highscorePopup = new VBox();
        highscorePopup.setAlignment(Pos.CENTER);
        highscorePopup.getChildren().add(playersPoints);
        highscorePopup.getChildren().add(enterHighscore);
        HBox highscoreBox = new HBox();
        highscoreBox.getChildren().add(name);
        highscoreBox.getChildren().add(ok);
        highscorePopup.getChildren().add(highscoreBox);
        highscorePopup.setLayoutX(root.getScene().getWidth() / 2 - 120);
        highscorePopup.setLayoutY(root.getScene().getHeight() - 100);
        root.getChildren().add(highscorePopup);

        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // Create a new highscore object and save it to the highscore list
                entry = new HighscoreEntry(name.getText(), finalScore);
                entry.save();
                gameOver();
            }
        });


    }

    private void introSequence() {
        Label ready = new Label("READY?");

        ready.setLayoutX(root.getScene().getWidth() / 2);
        ready.setLayoutY(root.getScene().getHeight() / 2);

        if (gameWasPaused) {

            timeline.pause();
            root.getChildren().add(ready);
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    Platform.runLater(() -> {
                        ready.setText("START!");
                    });

                }
            }, 1000);

            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    Platform.runLater(() -> {

                        timeline.play();
                        player1.toggleMoveable();
                        player2.toggleMoveable();
                        root.getChildren().remove(ready);
                    });
                }

            }, 2000);

            gameWasPaused = false;
        }


    }

    private void moveAllBullets() {
        List<Bullet> bulletsToDelete = new ArrayList<Bullet>();
        for (Bullet b : bulletList) {
            b.move();
            if (!b.isActive()) {
                bulletsToDelete.add(b);
            }
        }
        for (Bullet b : bulletsToDelete) {
            bulletList.remove(b);
            root.getChildren().remove(b.getGroup());
        }
    }

    private void moveAllEnemies() {
        List<Enemy> enemiesToDelete = new ArrayList<Enemy>();

        if (enemyList.isEmpty()) {
            enterHighscore();
        }

        for (Enemy e : enemyList) {
            if (e.isAlive()) {
                e.move();
                int d = (int) (FPS * (1 / SHOOT_LIKELIHOOD));
                int random = new Random().nextInt(d);
                if (random == 0) {
                    e.shoot();
                }
            } else {
                enemiesToDelete.add(e);
            }
        }

        for (Enemy e : enemiesToDelete) {
            enemyList.remove(e);
            root.getChildren().remove(e.getGroup());
            player1.getTargets().remove(e);
            player2.getTargets().remove(e);
        }
    }

    private void gameOver() {
        final GameOver gameOver = new GameOver();
        gameOver.start(primaryStage);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void pauseGame() {

        if (gameWasPaused) {
            root.getChildren().remove(pause);
            timeline.play();
            gameWasPaused = false;
        } else {
            pause.setLayoutX(root.getScene().getWidth() / 2);
            pause.setLayoutY(root.getScene().getHeight() / 2);
            root.getChildren().add(pause);
            timeline.pause();
            gameWasPaused = true;
        }
    }

    public void muteMusic() {
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }

    private class ShootCallbackImpl implements ShootCallback {
        @Override
        public void shootBullet(final Bullet bullet) {
            bulletList.add(bullet);
            root.getChildren().add(bullet.getGroup());
        }
    }
}
