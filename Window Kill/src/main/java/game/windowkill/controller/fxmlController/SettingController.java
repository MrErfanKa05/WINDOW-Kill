package game.windowkill.controller.fxmlController;

import game.windowkill.controller.DataHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController extends Controller implements Initializable {
    @FXML
    Button back;
    @FXML
    Slider sound;
    @FXML
    Slider difficulty;
    @FXML
    Slider sensitive;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> Exit());
    }
    public void Exit(){
        DataHolder.difficulty = difficulty.getValue();
        DataHolder.soundVolume = sound.getValue();
        DataHolder.sensitivity = sensitive.getValue();
        setMenu();
    }
}
