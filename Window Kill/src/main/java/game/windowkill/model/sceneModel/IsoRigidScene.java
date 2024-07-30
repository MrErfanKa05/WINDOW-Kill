package game.windowkill.model.sceneModel;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import javafx.geometry.Point2D;

public class NormalSceneModel extends BaseSceneModel implements {

    public NormalSceneModel(double left, double right, double top, double down) {
        super();
        set(left, right, top, down);
    }

    @Override
    public void reset() {

    }
}
