package game.windowkill.controller.fxmlController;

import game.windowkill.controller.net.Manager;
import game.windowkill.model.characterModel.EpsilonModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NameController extends Controller implements Initializable {

    private static String s = "";
    @FXML
    Button enter;
    @FXML
    Label error;
    @FXML
    TextField text;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enter.setOnAction(actionEvent -> Enter());
    }
    public void Enter(){
//        if(!s.equals("")){
//            error.setText(s);
//            s = "";
//            return;
//        }
        if(text.equals("")) {
            error.setText("invalid user name");
            return;
        }
        for(int i = 0; i < text.getText().length(); i++){
            if(text.getText().charAt(i) == ' '){
                error.setText("use a-z A-Z 0-9 only");
                return;
            }
            if(text.getText().charAt(i) >= 'a' && text.getText().charAt(i) <= 'z')
                continue;
            else if(text.getText().charAt(i) >= 'A' && text.getText().charAt(i) <= 'Z')
                continue;
            else if(text.getText().charAt(i) >= '0' && text.getText().charAt(i) <= '9')
                continue;
            else {
                error.setText("use a-z A-Z 1-9 only");
                return;
            }
        }
        Manager.getInstance().createUser(text.getText());
    }
    public static void setError(String s){
        NameController.s = s;
    }
}
