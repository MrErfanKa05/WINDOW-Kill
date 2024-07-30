package game.windowkill.model.sceneModel;

import game.windowkill.view.scene.IsoNonRigidSceneView;
import game.windowkill.view.scene.IsoRigidSceneView;
import game.windowkill.view.scene.NonIsoNonRigidSceneView;

import java.util.ArrayList;

public class NonIsoNonRigidScene extends BaseSceneModel {
    public static ArrayList<NonIsoNonRigidScene> nonIsoNonRigidScenes = new ArrayList<>();

    public NonIsoNonRigidScene(double left, double right, double top, double down) {
        super();
        set(left, right, top, down);
        new NonIsoNonRigidSceneView(id, left, right, top, down);
    }

    public void remove(){
        NonIsoNonRigidSceneView view = null;
        for(NonIsoNonRigidSceneView nonIsoNonRigidSceneView : NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews)
            if(nonIsoNonRigidSceneView.getId().equals(getId()))
                view = nonIsoNonRigidSceneView;
        NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews.remove(view);
    }
}
