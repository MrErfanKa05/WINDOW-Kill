package game.windowkill.model.characterModel.multiScene;

import game.windowkill.model.sceneModel.BaseSceneModel;
import javafx.geometry.Point2D;

public interface LocalMultiScene {
    boolean isMultiScene();
    boolean isInsideMultiScene(Point2D point2D);
}
