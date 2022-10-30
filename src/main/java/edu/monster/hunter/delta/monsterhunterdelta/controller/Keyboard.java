package edu.monster.hunter.delta.monsterhunterdelta.controller;


import edu.monster.hunter.delta.monsterhunterdelta.model.Bullet;
import edu.monster.hunter.delta.monsterhunterdelta.model.Direction;
import edu.monster.hunter.delta.monsterhunterdelta.model.Player;
import edu.monster.hunter.delta.monsterhunterdelta.view.PlayFieldScreen;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard implements EventHandler<KeyEvent> {

    private static final Image IMAGE_UP = new Image(
            Bullet.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/sprites.png"));
    private static final Image IMAGE_DOWN = new Image(
            Bullet.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/sprites.png"));
    private static final Image IMAGE_LEFT = new Image(
            Bullet.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/sprites.png"));
    private static final Image IMAGE_RIGHT = new Image(
            Bullet.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/sprites.png"));
    private final Player player1;
    private final Player player2;
    private final PlayFieldScreen screen;

    public Keyboard(Player player1, Player player2, PlayFieldScreen screen) {
        this.player1 = player1;
        this.player2 = player2;
        this.screen = screen;
    }

    @Override
    public void handle(KeyEvent e) {
        KeyCode code = e.getCode();

        switch (code) {

            case W:
                if (player1.isMovable()) {
                    player1.setDirection(Direction.UP);
                    player1.move();
                    player1.getImageView().setImage(IMAGE_UP);
                }
                break;
            case S:
                if (player1.isMovable()) {
                    player1.setDirection(Direction.DOWN);
                    player1.move();
                    player1.getImageView().setImage(IMAGE_DOWN);
                }
                break;
            case A:
                if (player1.isMovable()) {
                    player1.setDirection(Direction.LEFT);
                    player1.move();
                    player1.getImageView().setImage(IMAGE_LEFT);
                }
                break;
            case D:
                if (player1.isMovable()) {
                    player1.setDirection(Direction.RIGHT);
                    player1.move();
                    player1.getImageView().setImage(IMAGE_RIGHT);
                }
                break;
            case SPACE:
                if (player1.isMovable()) {
                    player1.shoot();
                }
                break;
            case UP:
                if (player2.isMovable()) {
                    player2.setDirection(Direction.UP);
                    player2.move();
                    player2.getImageView().setImage(IMAGE_UP);
                }
                break;
            case DOWN:
                if (player2.isMovable()) {
                    player2.setDirection(Direction.DOWN);
                    player2.move();
                    player2.getImageView().setImage(IMAGE_DOWN);
                }
                break;
            case LEFT:
                if (player2.isMovable()) {
                    player2.setDirection(Direction.LEFT);
                    player2.move();
                    player2.getImageView().setImage(IMAGE_LEFT);
                }
                break;
            case RIGHT:
                if (player2.isMovable()) {
                    player2.setDirection(Direction.RIGHT);
                    player2.move();
                    player2.getImageView().setImage(IMAGE_RIGHT);
                }
                break;
            case O:
                if (player2.isMovable()) {
                    player2.shoot();
                }
                break;
            case P:
                screen.pauseGame();
                player1.toggleMoveable();
                break;
            case M:
                screen.muteMusic();
            default:
                break;

        }

    }
}
