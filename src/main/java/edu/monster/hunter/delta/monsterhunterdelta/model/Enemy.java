package edu.monster.hunter.delta.monsterhunterdelta.model;

import javafx.scene.image.Image;

import java.util.Random;

public class Enemy extends ShootingFigure {

    private static final Image BURWOR_IMAGE = new Image(Enemy.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/BURWOR.png"));
    private static final Image GARWOR_IMAGE = new Image(Enemy.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/GARWOR.png"));
    private static final Image THORWOR_IMAGE = new Image(Enemy.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/THORWOR.png"));
    private static final Image WIZARD_IMAGE = new Image(Enemy.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/WIZARD.png"));
    private static final Image WORLUK_IMAGE = new Image(Enemy.class.getResourceAsStream("/edu/monster/hunter/delta/monsterhunterdelta/WORLUK.png"));


    public Enemy(final Maze maze, final TypeOfFigure type, final double x,
                 final double y) {
        super(maze, type, x, y);
        setImageByMonster(type);
        getImageView().setX(x);
        getImageView().setY(y);
    }

    public String getName() {
        return getType().name();
    }

    private void setImageByMonster(final TypeOfFigure type) {
        switch (type) {
            case BURWOR:
                getImageView().setImage(BURWOR_IMAGE);
                break;
            case GARWOR:
                getImageView().setImage(GARWOR_IMAGE);
                break;
            case THORWOR:
                getImageView().setImage(THORWOR_IMAGE);
                break;
            case WIZARD:
                getImageView().setImage(WIZARD_IMAGE);
                break;
            case WORLUK:
                getImageView().setImage(WORLUK_IMAGE);
                break;
            default:
                break;
        }
    }


    @Override
    public void onCollisionWithMaze() {
        changeToRandomDirection();
    }

    private void changeToRandomDirection() {
        int random = new Random().nextInt(4);

        Direction futureDirection = Direction.values()[random];

        setDirection(futureDirection);
    }

    @Override
    public void move() {
        if (willCollideWithMazeInFuture()) {
            onCollisionWithMaze();
        } else {

            int likelihood = (int) (30 * (1 / 0.7));
            int random = new Random().nextInt(likelihood);
            if (random == 0) {
                changeToRandomDirection();
            }
            super.move();
        }

    }

    @Override
    public void bulletHasHitATarget(final Figure target) {
        super.bulletHasHitATarget(target);

        target.setAlive(false);
    }

}
