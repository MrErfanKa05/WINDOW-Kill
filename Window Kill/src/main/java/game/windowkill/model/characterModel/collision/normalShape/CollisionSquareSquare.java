package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

public interface CollisionSquareSquare extends ModeInitialize {
    default boolean checkSquareSquare(Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        return (collision(p1) || collision(p2) || collision(p3) || collision(p4));
    }
    default Point2D getSquareSquare(Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        if(collision(p1))
            return p1;
        if(collision(p2))
            return p2;
        if(collision(p3))
            return p3;
        if(collision(p4))
            return p4;
        return null;
    }

}
