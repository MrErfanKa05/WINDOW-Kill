package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.controller.Constant;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

import static java.lang.Math.max;
import static java.lang.Math.min;

public interface CollisionSquareCircle extends ModeInitialize {
    default public boolean checkSquareCircle(Point2D p1, Point2D p2, Point2D p3, Point2D p4,
                                                   double circleCenterX, double circleCenterY, double radius) {
        double squareTopLeftX = min(p1.getX(), min(p2.getX(), min(p3.getX(), p4.getX())));
        double squareTopLeftY = min(p1.getY(), min(p2.getY(), min(p3.getY(), p4.getY())));
        double squareBottomRightX = max(p1.getX(), max(p2.getX(), max(p3.getX(), p4.getX())));
        double squareBottomRightY = max(p1.getY(), max(p2.getY(), max(p3.getY(), p4.getY())));

        double nearestX = max(squareTopLeftX, Math.min(circleCenterX, squareBottomRightX));
        double nearestY = max(squareTopLeftY, Math.min(circleCenterY, squareBottomRightY));

        double deltaX = nearestX - circleCenterX;
        double deltaY = nearestY - circleCenterY;

        return (deltaX * deltaX + deltaY * deltaY) < (radius * radius);
    }



    default Point2D getSquareCircle(Point2D p1, Point2D p2, Point2D p3, Point2D p4, double x, double y, double radius){
        Point2D ans = null;
        Point2D f1 = pointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y);
        Point2D f2 = pointInsideCircle(p3.getX(), p3.getY(), p2.getX(), p2.getY(), x, y);
        Point2D f3 = pointInsideCircle(p1.getX(), p1.getY(), p4.getX(), p4.getY(), x, y);
        Point2D f4 = pointInsideCircle(p4.getX(), p4.getY(), p3.getX(), p3.getY(), x, y);
        double dis1 = pointLineDistance(p1.getX(), p1.getY(), p2.getX(), p2.getY(), x, y);
        double dis2 = pointLineDistance(p3.getX(), p3.getY(), p2.getX(), p2.getY(), x, y);
        double dis3 = pointLineDistance(p1.getX(), p1.getY(), p4.getX(), p4.getY(), x, y);
        double dis4 = pointLineDistance(p4.getX(), p4.getY(), p3.getX(), p3.getY(), x, y);
        if(dis3 <= radius) ans = f3;
        if(dis2 <= radius) ans = f2;
        if(dis1 <= radius) ans = f1;
        if(dis4 <= radius) ans = f4;
        return ans;
    }
}
