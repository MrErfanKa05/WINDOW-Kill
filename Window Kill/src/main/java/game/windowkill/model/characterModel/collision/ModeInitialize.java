package game.windowkill.model.characterModel.collision;

import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;
import static java.lang.Math.*;

public interface ModeInitialize extends Collidable {
    default double getDistance(Point2D point1, Point2D point2){
        return Math.sqrt((point2.getX() - point1.getX()) * (point2.getX() - point1.getX()) +
                (point2.getY() - point1.getY()) * (point2.getY() - point1.getY()));
    }
    default boolean checkPointInsideCircle(double x1, double y1, double x2, double y2, double x, double y, double radius){
        double a = y2 - y1;
        double b = x1 - x2;
        double c = x2 * y1 - x1 * y2;

        // محاسبه فاصله نقطه به خط
        double distance = Math.abs(a * x + b * y + c) / Math.sqrt(a * a + b * b);
        double xM = (b * (b * x - a * y) - a * c) / (a * a + b * b);
        double yM = (a * (-b * x + a * y) - b * c) / (a * a + b * b);
//        double xM = getPointInsideCircle(x1, y1, x2, y2, x, y, radius).getX();
//        double yM = getPointInsideCircle(x1, y1, x2, y2, x, y, radius).getY();
        if(xM <= max(x1, x2) && xM >= min(x1, x2) && yM <= max(y1, y2) && yM >= min(y1, y2))
            if(distance <= radius)
                return true;
        return false;

    }
    default Point2D getPointInsideCircle(double x1, double y1, double x2, double y2, double x, double y, double radius){
        double a = y2 - y1;
        double b = x1 - x2;
        double c = x2 * y1 - x1 * y2;

        // محاسبه فاصله نقطه به خط
        double distance = Math.abs(a * x + b * y + c) / Math.sqrt(a * a + b * b);
        double ansX = (b * (b * x - a * y) - a * c) / (a * a + b * b);
        double ansY = (a * (-b * x + a * y) - b * c) / (a * a + b * b);
        return new Point2D(ansX, ansY);
    }
    default boolean isPointInsideSquare(double x1, double y1, double sideLength, double px, double py) {
        double x2 = x1 + sideLength;
        double y2 = y1 + sideLength;

        return px >= x1 && px <= x2 && py >= y1 && py <= y2;
    }
}
