package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

public interface CollisionSquareTriangle extends ModeInitialize {
    default boolean checkSquareTriangle(Entity entity, Point2D p1, Point2D p2, Point2D p3){
        return entity.collision(p1) || entity.collision(p2) || entity.collision(p3);
    }
    default boolean checkSquareTriangle(Entity entity, Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        return entity.collision(p1) || entity.collision(p2) || entity.collision(p3) || entity.collision(p4);
    }
    default Point2D getSquareTriangle(Entity entity, Point2D p1, Point2D p2, Point2D p3){
        if(entity.collision(p1))
            return p1;
        if(entity.collision(p2))
            return p2;
        if(entity.collision(p3))
            return p3;
        return null;
    }
    default Point2D getSquareTriangle(Entity entity, Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        if(entity.collision(p1))
            return p1;
        if(entity.collision(p2))
            return p2;
        if(entity.collision(p3))
            return p3;
        if(entity.collision(p4))
            return p4;
        return null;
    }
}
