package edu.monster.hunter.delta.monsterhunterdelta.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Options extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Monster Hunter Arcade - Optionen");
        primaryStage.setResizable(false);

        GridPane grid = new GridPane();
        grid.setId("optionsGrid");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);

        Label optionHeader = new Label("OPTIONEN");
        grid.add(optionHeader, 0, 0);

        Label controls = new Label("Steuerung");
        grid.add(controls, 0, 1);

        Label configuration = new Label("Hoch \t W \n"
                + "Runter \t S \n"
                + "Links \t A \n"
                + "Rechts \t D \n"
                + "Schießen  Leertaste \n"
                + "Musik an/aus M \n"
                + "Pause \t P");
        grid.add(configuration, 0, 3);

        Button okBtn = new Button();
        okBtn.setText("Zurück");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                TitleScreen title = new TitleScreen();
                title.start(primaryStage);
            }

        });

        grid.add(okBtn, 0, 4);
        Scene scene = new Scene(grid, 1024, 740);
        scene.getStylesheets().add(TitleScreen.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/controls.css").toExternalForm());
        scene.getStylesheets().add(
                Credits.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/Options.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
