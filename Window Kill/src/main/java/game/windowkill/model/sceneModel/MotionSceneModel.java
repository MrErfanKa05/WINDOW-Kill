package game.windowkill.model.sceneModel;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.scenes.CollisionCircleScene;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.sceneModel.property.NonIsometric;
import game.windowkill.model.sceneModel.property.Rigid;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MotionSceneModel extends BaseSceneModel implements CollisionCircleScene, NonIsometric, Rigid {

    private static MotionSceneModel model;
    public static MotionSceneModel getInstance(){
        if(model == null) model = new MotionSceneModel();
        return model;
    }
    private MotionSceneModel(){
        super();
        reset();
    }

    @Override
    public void reset(){
        direction = new Direction(0, 0);

        top = SCREEN_HEIGHT/2 - MAX_HEIGHT /2;
        down = SCREEN_HEIGHT/2 + MAX_HEIGHT /2;
        right = SCREEN_WIDTH/2 + MAX_WIDTH /2;
        left = SCREEN_WIDTH/2 - MAX_WIDTH /2;

        width = MOTION_SCENE_WIDTH;
        height = MOTION_SCENE_HEIGHT;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof EpsilonModel) {
            return checkCircleScene(entity, top, down, left, right);
        }
        return false;
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof EpsilonModel) {
            Point2D p1 = new Point2D(entity.getPosition().getX(), top);
            Point2D p2 = new Point2D(entity.getPosition().getX(), down);
            Point2D p3 = new Point2D(right, entity.getPosition().getY());
            Point2D p4 = new Point2D(left, entity.getPosition().getY());
            return getCircleScene(entity, p1, p2, p3, p4);
        }

        return null;
    }
}