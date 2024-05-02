package game.windowkill;

import game.windowkill.game.WindowKill;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new WindowKill(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}