package game.windowkill.controller.fxmlController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class TutorialController extends Controller implements Initializable {
    @FXML
    Button back;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> Back());
    }
    public void Back(){
        setMenu();
    }
}
