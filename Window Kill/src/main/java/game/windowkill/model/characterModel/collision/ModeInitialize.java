package game.windowkill.model.characterModel.collision;

import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;
import static java.lang.Math.abs;

public interface ModeInitialize extends Collidable {
    default public double getDistance(Point2D point1, Point2D point2){
        return Math.sqrt(point2.getX() * point1.getX() + point2.getY() * point1.getY());
    }
    default public double pointLineDistance(double x1, double y1, double x2, double y2, double px, double py) {
        double norm = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return abs((px - x1) * (y2 - y1) - (py - y1) * (x2 - x1)) / norm;
    }
    default public boolean checkPointInsideCircle(double x1, double y1, double x2, double y2, double x, double y, double radius){
        Point2D point = pointInsideCircle(x1, y1, x2, y2, x, y);
        return getDistance(point, new Point2D(x + EPSILON_RADIUS, y + EPSILON_RADIUS)) <= radius;
    }
    default public Point2D pointInsideCircle(double x1, double y1, double x2, double y2, double x, double y){
        double lineLengthX = x2 - x1;
        double lineLengthY = y2 - y1;

        double t = ((x + 10 - x1) * lineLengthX + (y + 10 - y1) * lineLengthY) /
                (lineLengthX * lineLengthX + lineLengthY * lineLengthY);
        t = Math.max(0, Math.min(t, 1));

        double nearestX = x1 + t * lineLengthX;
        double nearestY = y1 + t * lineLengthY;

        return new Point2D(nearestX, nearestY);
    }
    default public boolean isPointInsideSquare(double x1, double y1, double sideLength, double px, double py) {
        double x2 = x1 + sideLength;
        double y2 = y1 + sideLength;

        return px >= x1 && px <= x2 && py >= y1 && py <= y2;
    }
}
