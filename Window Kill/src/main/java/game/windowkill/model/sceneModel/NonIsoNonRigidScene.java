package game.windowkill.model.sceneModel;

import game.windowkill.model.sceneModel.property.NonRigid;
import game.windowkill.model.sceneModel.property.NonIsometric;
import game.windowkill.view.scene.NonIsoNonRigidSceneView;

import java.util.ArrayList;

public class NonIsoNonRigidScene extends BaseSceneModel implements NonIsometric, NonRigid {
    public static ArrayList<NonIsoNonRigidScene> nonIsoNonRigidScenes = new ArrayList<>();

    public NonIsoNonRigidScene(double left, double right, double top, double down) {
        super();
        set(left, right, top, down);
        new NonIsoNonRigidSceneView(id, left, right, top, down);
        nonIsoNonRigidScenes.add(this);
    }

    public void remove(){
        NonIsoNonRigidSceneView view = null;
        for(NonIsoNonRigidSceneView nonIsoNonRigidSceneView : NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews)
            if(nonIsoNonRigidSceneView.getId().equals(getId()))
                view = nonIsoNonRigidSceneView;
        NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews.remove(view);
        view.stage.close();
    }
}
