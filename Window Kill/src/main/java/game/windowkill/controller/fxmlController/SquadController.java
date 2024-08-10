package game.windowkill.controller.fxmlController;

import game.windowkill.controller.net.Manager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SquadController extends Controller implements Initializable {

    @FXML
    VBox vBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> enemy = Manager.getInstance().getEnemy();
        for(String s : enemy){
            Button button = new Button(s);
            vBox.getChildren().add(button);
            button.setOnAction(actionEvent -> {
                Manager.getInstance().newGame(button.getText());
            });
        }
    }
}
