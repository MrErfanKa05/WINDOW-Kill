package game.windowkill.model.sceneModel;

import game.windowkill.model.sceneModel.property.Rigid;
import game.windowkill.view.scene.IsoRigidSceneView;
import game.windowkill.view.scene.NonIsoRigidSceneView;

import java.util.ArrayList;

public class NonIsoRigidScene extends BaseSceneModel implements Rigid {
    public static ArrayList<NonIsoRigidScene> nonIsoRigidScenes = new ArrayList<>();

    public NonIsoRigidScene(double left, double right, double top, double down) {
        super();
        set(left, right, top, down);
        nonIsoRigidScenes.add(this);
        addRigid(this);
        new NonIsoRigidSceneView(id, left, right, top, down);
    }

    public void remove(){
        nonIsoRigidScenes.remove(this);
        removeRigid(this);
        NonIsoRigidSceneView view = null;
        for(NonIsoRigidSceneView nonIsoRigidSceneView : NonIsoRigidSceneView.nonIsoRigidSceneViews)
            if(nonIsoRigidSceneView.getId().equals(getId()))
                view = nonIsoRigidSceneView;
        NonIsoRigidSceneView.nonIsoRigidSceneViews.remove(view);
    }
}
