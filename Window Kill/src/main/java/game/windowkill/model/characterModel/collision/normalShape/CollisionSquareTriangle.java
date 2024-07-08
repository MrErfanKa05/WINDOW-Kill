package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

public interface CollisionSquareTriangle extends ModeInitialize {
    default boolean checkSquareTriangle(Point2D p1, Point2D p2, Point2D p3){
        boolean b1, b2, b3;
        b1 = collision(p1);
        b2 = collision(p2);
        b3 = collision(p3);
        return (b1 || b2 || b3);
    }
    default Point2D getSquareTriangle(Point2D p1, Point2D p2, Point2D p3){
        boolean b1, b2, b3;
        b1 = collision(p1);
        b2 = collision(p2);
        b3 = collision(p3);
        if (b1) return p1;
        if (b2) return p2;
        if (b3) return p3;
        return null;
    }
}
