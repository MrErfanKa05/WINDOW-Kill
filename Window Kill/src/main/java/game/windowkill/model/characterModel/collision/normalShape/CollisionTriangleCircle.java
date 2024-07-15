package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.controller.Constant;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;
import static java.lang.Math.abs;

public interface CollisionTriangleCircle extends ModeInitialize {
    default boolean checkTriangleCircle(Point2D p1, Point2D p2, Point2D p3, double x, double y, double radius){
        return checkPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y, radius) ||
                checkPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY(), x, y, radius) ||
                checkPointInsideCircle(p1.getX(), p1.getY(), p3.getX(), p3.getY(), x, y, radius);
    }

    default Point2D getTriangleCircle(Point2D p1, Point2D p2, Point2D p3, double x, double y, double radius){
        boolean b1 = checkPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y, radius);
        boolean b2 = checkPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY(), x, y, radius);
        boolean b3 = checkPointInsideCircle(p1.getX(), p1.getY(), p3.getX(), p3.getY(), x, y, radius);

        if(b1)return getPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y, radius);
        if(b2)return getPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY(), x, y, radius);
        if(b3)return getPointInsideCircle(p1.getX(), p1.getY(), p3.getX(), p3.getY(), x, y, radius);
        return null;
    }
}
