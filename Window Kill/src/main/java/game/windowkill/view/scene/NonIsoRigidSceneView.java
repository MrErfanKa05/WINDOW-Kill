package game.windowkill.view.scene;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class NonIsoRigidSceneView extends SceneView{
    public static ArrayList<NonIsoRigidSceneView> nonIsoRigidSceneViews = new ArrayList<>();
    public NonIsoRigidSceneView(String id, double left, double right, double top, double down) {
        super(id, left, right, top, down);
        nonIsoRigidSceneViews.add(this);
    }
}
