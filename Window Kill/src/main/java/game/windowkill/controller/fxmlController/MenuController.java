package game.windowkill.controller.fxmlController;

import game.windowkill.controller.DataHolder;
import game.windowkill.controller.Update;
import game.windowkill.controller.net.Manager;
import game.windowkill.view.scene.MotionSceneView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {
    @FXML
    Button start;
    @FXML
    Button exit;
    @FXML
    Button setting;
    @FXML
    Button tutorial;
    @FXML
    Button skillTree;
    @FXML
    Button online;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnAction(actionEvent -> Exit());
        setting.setOnAction(actionEvent -> Setting());
        skillTree.setOnAction(actionEvent -> SkillTree());
        tutorial.setOnAction(actionEvent -> Tutorial());
        start.setOnAction(actionEvent -> Start());
        online.setOnAction(actionEvent -> Online());

    }
    public void Online(){
        Manager.getInstance().connect();
    }
    public void SkillTree(){
        setSkillTree();
    }
    public void Start(){
        MotionSceneView.getInstance().init();
        new Update();
    }
    public void Setting(){
        setSetting();
    }
    public void Tutorial(){
        setTutorial();
    }
    public void Exit(){
        DataHolder.stage.close();
    }
}
