module game.windowkill {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    exports game.windowkill;
    opens game.windowkill.controller.fxmlController to javafx.fxml;
}