package game.windowkill.model.characterModel.collision.scenes;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;

public interface CollisionCircleScene extends ModeInitialize {
    default boolean checkCircleScene(Entity entity, double top, double down, double left, double right){
         return entity.collision(new Point2D(entity.getPosition().getX(), top)) ||
                entity.collision(new Point2D(entity.getPosition().getX() , down)) ||
                entity.collision(new Point2D(left, entity.getPosition().getY())) ||
                entity.collision(new Point2D(right, entity.getPosition().getY()));
    }

    default Point2D getCircleScene(Entity entity, Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        if (entity.collision(p1))
            return p1;
        if (entity.collision(p2))
            return p2;
        if (entity.collision(p3))
            return p3;
        if (entity.collision(p4))
            return p4;
        return null;
    }
}
