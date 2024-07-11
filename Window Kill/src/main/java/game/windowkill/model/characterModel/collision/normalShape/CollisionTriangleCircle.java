package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.controller.Constant;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;
import static java.lang.Math.abs;

public interface CollisionTriangleCircle extends ModeInitialize {
    default boolean checkTriangleCircle(Point2D p1, Point2D p2, Point2D p3, double x, double y, double radius){
        return checkPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y, radius) &&
                checkPointInsideCircle(p1.getX(), p1.getY(), p3.getX(), p3.getY(), x, y, radius) &&
                checkPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY(), x, y, radius);
    }

    default Point2D getTriangleCircle(Point2D p1, Point2D p2, Point2D p3, double x, double y, double radius){
        Point2D ans = null;
        Point2D f1 = pointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y);
        Point2D f2 = pointInsideCircle(p3.getX(), p3.getY(), p2.getX(), p2.getY(), x, y);
        Point2D f3 = pointInsideCircle(p1.getX(), p1.getY(), p3.getX(), p3.getY(), x, y);
        double dis1 = pointLineDistance(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y);
        double dis2 = pointLineDistance(p3.getX(), p3.getY(), p2.getX(), p2.getY(), x, y);
        double dis3 = pointLineDistance(p1.getX(), p1.getY(), p3.getX(), p3.getY(), x, y);
        if(dis3 <= radius)ans = f3;
        if(dis2 <= radius)ans = f2;
        if(dis1 <= radius)ans = f1;
        return ans;
    }
}
