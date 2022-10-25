module edu.monster.hunter.delta.monsterhunterdelta {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.media;


    opens edu.monster.hunter.delta.monsterhunterdelta.view to javafx;
    opens edu.monster.hunter.delta.monsterhunterdelta.model to javafx;



    opens edu.monster.hunter.delta.monsterhunterdelta to javafx.fxml;
    exports edu.monster.hunter.delta.monsterhunterdelta.view;
    exports edu.monster.hunter.delta.monsterhunterdelta.model;
    exports edu.monster.hunter.delta.monsterhunterdelta;
}