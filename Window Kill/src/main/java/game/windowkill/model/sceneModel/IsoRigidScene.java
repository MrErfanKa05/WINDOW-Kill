package game.windowkill.model.sceneModel;

import game.windowkill.model.sceneModel.property.Rigid;
import game.windowkill.model.sceneModel.property.Isometric;
import game.windowkill.view.scene.IsoRigidSceneView;

import java.util.ArrayList;

public class IsoRigidScene extends BaseSceneModel implements Rigid, Isometric {
    public static ArrayList<IsoRigidScene> isoRigidScenes = new ArrayList<>();

    public IsoRigidScene(double left, double right, double top, double down) {
        super();
        set(left, right, top, down);
        isoRigidScenes.add(this);
        addRigid(this);
        addIsometric(this);
        new IsoRigidSceneView(id, left, right, top, down);
        width = right - left;
        height = down- top;
    }

    public void remove(){
        isoRigidScenes.remove(this);
        removeRigid(this);
        removeRigid(this);
        IsoRigidSceneView view = null;
        for(IsoRigidSceneView isoRigidSceneView : IsoRigidSceneView.isoRigidSceneViews)
            if(isoRigidSceneView.getId().equals(getId()))
                view = isoRigidSceneView;
        IsoRigidSceneView.isoRigidSceneViews.remove(view);
        view.stage.close();
    }
}
