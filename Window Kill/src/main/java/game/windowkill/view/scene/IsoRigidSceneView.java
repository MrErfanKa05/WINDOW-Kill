package game.windowkill.view.scene;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class IsoRigidSceneView extends SceneView{
    public static ArrayList<IsoRigidSceneView> isoRigidSceneViews = new ArrayList<>();
    public IsoRigidSceneView(String id, double left, double right, double top, double down) {
        super(id, left, right, top, down);
        isoRigidSceneViews.add(this);
    }
}
