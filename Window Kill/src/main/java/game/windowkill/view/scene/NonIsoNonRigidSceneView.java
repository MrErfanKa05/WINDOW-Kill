package game.windowkill.view.scene;

import game.windowkill.model.sceneModel.NonIsoNonRigidScene;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class NonIsoNonRigidSceneView extends SceneView{
    public static ArrayList<NonIsoNonRigidSceneView> nonIsoNonRigidSceneViews = new ArrayList<>();
    public NonIsoNonRigidSceneView(String id, double left, double right, double top, double down) {
        super(id, left, right, top, down);
        nonIsoNonRigidSceneViews.add(this);
    }
}
