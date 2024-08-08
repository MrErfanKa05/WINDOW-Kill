package game.windowkill.model.sceneModel;

import game.windowkill.model.sceneModel.property.Isometric;
import game.windowkill.model.sceneModel.property.NonRigid;
import game.windowkill.model.sceneModel.property.Rigid;
import game.windowkill.model.sceneModel.property.NonIsometric;
import game.windowkill.view.scene.IsoNonRigidSceneView;

import java.util.ArrayList;

public class IsoNonRigidScene extends BaseSceneModel implements Isometric, NonRigid {
    public static ArrayList<IsoNonRigidScene> isoNonRigidScenes = new ArrayList<>();

    public IsoNonRigidScene(double left, double right, double top, double down) {
        super();
        set(left, right, top, down);
        isoNonRigidScenes.add(this);
        addIsometric(this);
        new IsoNonRigidSceneView(id, left, right, top, down);
    }

    public void remove(){
        IsoNonRigidSceneView view = null;
        for(IsoNonRigidSceneView isoNonRigidSceneView : IsoNonRigidSceneView.isoNonRigidSceneViews)
            if(isoNonRigidSceneView.getId().equals(getId()))
                view = isoNonRigidSceneView;
        IsoNonRigidSceneView.isoNonRigidSceneViews.remove(view);
        view.stage.close();
    }
}
