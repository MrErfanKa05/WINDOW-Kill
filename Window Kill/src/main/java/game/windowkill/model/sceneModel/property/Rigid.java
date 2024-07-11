package game.windowkill.model.sceneModel.property;

import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.SceneCollidable;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public interface Rigid extends SceneCollidable {
    ArrayList<? super Rigid> rigids = new ArrayList<>();

    @Override
    default boolean checkSceneScene(BaseSceneModel baseSceneModel, double left, double right, double top, double down) {
        if(rigids.contains(baseSceneModel))
            return (collision(new Point2D(baseSceneModel.getLeft(), baseSceneModel.getDown())) ||
                    collision(new Point2D(baseSceneModel.getLeft(), baseSceneModel.getTop())) ||
                    collision(new Point2D(baseSceneModel.getRight(), baseSceneModel.getDown())) ||
                    collision(new Point2D(baseSceneModel.getRight(), baseSceneModel.getTop())) ||
                    baseSceneModel.collision(new Point2D(left, down)) || baseSceneModel.collision(new Point2D(left, top)) ||
                    baseSceneModel.collision(new Point2D(right, down)) || baseSceneModel.collision(new Point2D(right, top)));
        return false;
    }

    default <T extends Rigid> void add(T t){
        rigids.add(t);
    }

}
