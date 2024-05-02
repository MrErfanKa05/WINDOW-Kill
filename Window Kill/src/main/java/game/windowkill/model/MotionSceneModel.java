package game.windowkill.model;

public class MotionSceneModel {
    public static MotionSceneModel model;
    public static MotionSceneModel getInstance(){
        if(model == null) model = new MotionSceneModel();
        return model;
    }

}
