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

public class Credits extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Monster Hunter Delta- Credits");
        primaryStage.setResizable(false);

        GridPane grid = new GridPane();
        grid.setId("creditsGrid");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);

        Label creditsTitle = new Label("CREDITS (2023)");
        grid.add(creditsTitle, 0, 0);

        Label developers = new Label("Entwickelt von");
        grid.add(developers, 0, 1);

        Label basti = new Label("Sebastian Nadler");
        grid.add(basti, 0, 2);

        Label quirin = new Label("Quirin Johannes Koch");
        grid.add(quirin, 0, 3);

        Label marina = new Label("Maryna Korovay");
        grid.add(marina, 0, 4);

        Label reason = new Label("Dieses Spiel wurde im Zuge des Kurses Patterns and Frameworks entwickelt");
        grid.add(reason, 0, 5);

        Button okBtn = new Button();
        okBtn.setText("Zurück");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                TitleScreen title = new TitleScreen();
                title.start(primaryStage);
            }

        });

        grid.add(okBtn, 0, 6);

        Scene scene = new Scene(grid, 1024, 740);

        // add css class the creditsTitle element
        creditsTitle.getStyleClass().add("label-credits-title");

        scene.getStylesheets().add(Credits.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/controls.css").toExternalForm());
        scene.getStylesheets().add(
                Credits.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/Credits.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
