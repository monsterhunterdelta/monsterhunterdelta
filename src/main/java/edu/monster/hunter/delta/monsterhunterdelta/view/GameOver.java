package edu.monster.hunter.delta.monsterhunterdelta.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOver extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("GAME OVER!");
        primaryStage.setResizable(false);

        Button button = new Button();
        button.setText("Zurück");
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                TitleScreen title = new TitleScreen();
                title.start(primaryStage);
            }
        });

        StackPane pane = new StackPane();
        pane.setId("gameOverPane");
        pane.getChildren().add(button);
        Scene scene = new Scene(pane, 1024, 740);
        scene.getStylesheets().add(GameOver.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/controls.css").toExternalForm());
        scene.getStylesheets().add(
                GameOver.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/GameOver.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
