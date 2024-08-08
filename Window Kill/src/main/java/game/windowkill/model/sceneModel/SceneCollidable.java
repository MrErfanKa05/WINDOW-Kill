package game.windowkill.model.sceneModel;

import game.windowkill.model.characterModel.collision.Collidable;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.SCREEN_HEIGHT;
import static game.windowkill.controller.Constant.SCREEN_WIDTH;
import static java.lang.Math.max;
import static java.lang.Math.min;

public interface SceneCollidable extends Collidable {
    default boolean checkSceneScene(BaseSceneModel baseSceneModel, double left, double right, double top, double down) {
        return false;
    }
    default void changeScene(BaseSceneModel baseSceneModel){

    }
    default void shrinkage(BaseSceneModel baseSceneModel){

    }

}
