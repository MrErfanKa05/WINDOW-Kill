package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.controller.Constant;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

import static java.lang.Math.max;
import static java.lang.Math.min;

public interface CollisionSquareCircle extends ModeInitialize {
    default public boolean checkSquareCircle(Point2D p1, Point2D p2, Point2D p3, Point2D p4,
                                                   double x, double y, double radius) {
        return checkPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y, radius) ||
                checkPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY(), x, y, radius) ||
                checkPointInsideCircle(p3.getX(), p3.getY(), p4.getX(), p4.getY(), x, y, radius) ||
                checkPointInsideCircle(p4.getX(), p4.getY(), p1.getX(), p1.getY(), x, y, radius);
    }



    default Point2D getSquareCircle(Point2D p1, Point2D p2, Point2D p3, Point2D p4, double x, double y, double radius){
        boolean b1 = checkPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y, radius);
        boolean b2 = checkPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY(), x, y, radius);
        boolean b3 = checkPointInsideCircle(p3.getX(), p3.getY(), p4.getX(), p4.getY(), x, y, radius);
        boolean b4 = checkPointInsideCircle(p4.getX(), p4.getY(), p1.getX(), p1.getY(), x, y, radius);

        if(b1)return getPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y, radius);
        if(b2)return getPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY(), x, y, radius);
        if(b3)return getPointInsideCircle(p3.getX(), p3.getY(), p4.getX(), p4.getY(), x, y, radius);
        if(b4)return getPointInsideCircle(p4.getX(), p4.getY(), p1.getX(), p1.getY(), x, y, radius);
        return null;
    }
}
