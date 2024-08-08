package game.windowkill.controller.fxmlController;

import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.view.scene.MotionSceneView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WinController extends Controller implements Initializable {
    @FXML
    Label xp;
    @FXML
    Label time;
    @FXML
    Button menu;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xp.setText("XP : " + EpsilonModel.getInstance().getXP());
        time.setText("Time : " + MotionSceneView.getInstance().getElapsedTimeInt());
        menu.setOnAction(actionEvent -> Menu());
    }
    public void Menu(){
        setMenu();
    }
}
