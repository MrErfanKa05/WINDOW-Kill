module game.windowkill {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports game.windowkill;
    opens game.windowkill.controller.fxmlController to javafx.fxml;
}