package game.windowkill.controller.fxmlController;

import game.windowkill.controller.net.Manager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {

    public static String type = "";
    @FXML
    AnchorPane anchorPaneS;
    @FXML
    AnchorPane anchorPaneL;
    @FXML
    VBox vBoxL;
    @FXML
    VBox vBoxS;
    @FXML
    Button squad, create, logout;
    @FXML
    Label xp, request;
    @FXML
    TextField text;

    private static ArrayList<String> squads = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        squads = Manager.getInstance().getSquad();
        request.setText(type);
        type = "";
        for(String s : squads){
            Button button = new Button(s);
            vBoxS.getChildren().add(button);
            button.setOnAction(actionEvent -> {
                Manager.getInstance().wantToJoin(button.getText());
            });
        }
        logout.setOnAction(actionEvent -> {
            Manager.getInstance().close();
        });
        squad.setOnAction(actionEvent -> {
            Manager.getInstance().gotoSquad();
        });
        create.setOnAction(actionEvent -> {
            if(!text.equals(""))
                Manager.getInstance().createSquad(text.getText());
        });
        xp.setText(String.valueOf(Manager.getInstance().getXp()));


        anchorPaneL.setPrefHeight(vBoxL.getHeight());
        anchorPaneL.setMinHeight(vBoxL.getHeight());
        anchorPaneL.setMaxHeight(vBoxL.getHeight());
        anchorPaneS.setPrefHeight(vBoxS.getHeight());
        anchorPaneS.setMinHeight(vBoxS.getHeight());
        anchorPaneS.setMaxHeight(vBoxS.getHeight());
    }
}
