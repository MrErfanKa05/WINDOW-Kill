package game.windowkill.view;

import game.windowkill.controller.DataHolder;
import game.windowkill.controller.InputManager;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MotionSceneView {
    public static MotionSceneView model;
    private Stage stage;
    private Scene scene;
    private Pane pane;
    public InputManager inputManager;

    public static MotionSceneView getInstance() {
        if(model == null)model = new MotionSceneView(DataHolder.stage);
        return model;
    }
    private MotionSceneView(Stage stage){
        this.stage = stage;
        inputManager = new InputManager(scene);
    }
    public void init(){
        pane = new Pane();
        scene = new Scene(pane, 500, 500);
        stage.setScene(scene);
    }
    public void paint(){

    }
}
