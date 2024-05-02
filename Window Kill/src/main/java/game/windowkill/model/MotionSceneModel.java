package game.windowkill.model;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.*;

public class MotionSceneModel implements Collidable {
    Direction direction;
    double top, left, right, down;

    public static MotionSceneModel model;
    public static MotionSceneModel getInstance(){
        if(model == null) model = new MotionSceneModel();
        return model;
    }
    private MotionSceneModel(){
        direction = new Direction(0, 0);

        top = SCREEN_HEIGHT/2 - MAX_HEIGHT /2;
        down = SCREEN_HEIGHT/2 + MAX_HEIGHT /2;
        right = SCREEN_WIDTH/2 + MAX_WIDTH /2;
        left = SCREEN_WIDTH/2 - MAX_WIDTH /2;
    }

    @Override
    public boolean collision(Point2D point2D) {
        return false;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        return false;
    }
}
