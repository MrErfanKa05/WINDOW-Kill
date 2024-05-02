package game.windowkill.game;

import game.windowkill.controller.DataHolder;
import game.windowkill.controller.Update;
import javafx.stage.Stage;

public class WindowKill {
    public WindowKill(Stage stage){
        DataHolder.stage = stage;
        new Update();
    }
}
