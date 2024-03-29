package edu.monster.hunter.delta.monsterhunterdelta.view;

import edu.monster.hunter.delta.monsterhunterdelta.controller.HighscoreEntry;
import edu.monster.hunter.delta.monsterhunterdelta.controller.HighscoreEntryList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HighscoreScreen extends Application {

    @Override
    public void start(final Stage primaryStage) {

        primaryStage.setTitle("Monster Hunter Delta - Highscore");
        primaryStage.setResizable(false);

        GridPane grid = new GridPane();
        grid.setId("highscoreGrid");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);

        final Label highscoreTitle = new Label("Highscore");


        HighscoreEntryList highscoreEntryList = new HighscoreEntryList(new HighscoreEntry[50]);
        highscoreEntryList.loadHighscores();

        // for every HighscoreEntry in the list, add it to an ObservableList
        ObservableList<HighscoreEntry> highscoreEntryObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < highscoreEntryList.getHighscoreEntryList().length; i++) {
            if (highscoreEntryList.getHighscoreEntryList()[i] != null) {
                highscoreEntryObservableList.add(highscoreEntryList.getHighscoreEntryList()[i]);
            }
        }


        TableView highscoreTable = new TableView();

        highscoreTable.setId("highscoreTable");

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("name"));
        nameCol.setSortable(false);
        nameCol.setResizable(false);

        TableColumn scoreCol = new TableColumn("Punkte");
        scoreCol.setMinWidth(150);
        scoreCol.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("score"));
        scoreCol.setSortable(false);
        scoreCol.setResizable(false);

        highscoreTable.setItems(highscoreEntryObservableList);
        highscoreTable.getColumns().addAll(nameCol, scoreCol);

        Button okBtn = new Button();
        okBtn.setText("Zurück");
        okBtn.setAlignment(Pos.CENTER);
        okBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                TitleScreen title = new TitleScreen();
                title.start(primaryStage);
            }

        });

        grid.add(highscoreTitle, 0, 0);
        grid.add(highscoreTable, 0, 1);
        grid.add(okBtn, 0, 2);
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 1024, 740);
        scene.getStylesheets().add(HighscoreScreen.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/controls.css").toExternalForm());
        scene.getStylesheets().add(
                HighscoreScreen.class.getResource("/edu/monster/hunter/delta/monsterhunterdelta/HighscoreScreen.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
