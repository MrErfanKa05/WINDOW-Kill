package game.windowkill.model.sceneModel;

import game.windowkill.model.sceneModel.property.Isometric;
import game.windowkill.model.sceneModel.property.NonRigid;
import game.windowkill.model.sceneModel.property.Rigid;
import game.windowkill.model.sceneModel.property.NonIsometric;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MotionSceneModel extends BaseSceneModel implements NonRigid, Isometric {

    private static MotionSceneModel model;
    public static MotionSceneModel getInstance(){
        if(model == null) model = new MotionSceneModel();
        return model;
    }
    private MotionSceneModel(){
        super();
        addIsometric(this);
    }

    @Override
    public void update() {
        super.update();

        for(Object model : Isometric.isometrics){
            if(((BaseSceneModel) (model)).getId().equals(getId()))
                continue;
            checkForMultiScene((BaseSceneModel) model);
        }
    }

    @Override
    public void reset(){
        top = SCREEN_HEIGHT/2 - MAX_HEIGHT /2;
        down = SCREEN_HEIGHT/2 + MAX_HEIGHT /2;
        right = SCREEN_WIDTH/2 + MAX_WIDTH /2;
        left = SCREEN_WIDTH/2 - MAX_WIDTH /2;

        width = MOTION_SCENE_WIDTH;
        height = MOTION_SCENE_HEIGHT;
    }
}