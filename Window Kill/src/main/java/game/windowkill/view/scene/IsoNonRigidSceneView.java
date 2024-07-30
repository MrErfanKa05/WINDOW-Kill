package game.windowkill.view.scene;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class IsoNonRigidSceneView extends SceneView{
    public static ArrayList<IsoNonRigidSceneView> isoNonRigidSceneViews = new ArrayList<>();
    public IsoNonRigidSceneView(String id, double left, double right, double top, double down) {
        super(id, left, right, top, down);
        isoNonRigidSceneViews.add(this);
    }
}
