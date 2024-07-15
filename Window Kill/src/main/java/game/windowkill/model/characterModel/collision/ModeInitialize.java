package game.windowkill.model.characterModel.collision;

import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;
import static java.lang.Math.abs;

public interface ModeInitialize extends Collidable {
    default double getDistance(Point2D point1, Point2D point2){
        return Math.sqrt((point2.getX() - point1.getX()) * (point2.getX() - point1.getX()) +
                (point2.getY() - point1.getY()) * (point2.getY() - point1.getY()));
    }
    default boolean checkPointInsideCircle(double x1, double y1, double x2, double y2, double x, double y, double radius){
        double dx = x2 - x1;
        double dy = y2 - y1;
        double fx = x1 - x;
        double fy = y1 - y;

        double a = dx * dx + dy * dy;
        double b = 2 * (fx * dx + fy * dy);
        double c = (fx * fx + fy * fy) - radius * radius;

        double discriminant = b * b - 4 * a * c;

        // اگر مقدار دترمینان بزرگتر یا مساوی صفر باشد، خط با دایره برخورد دارد
        return discriminant >= 0;

    }
    default Point2D getPointInsideCircle(double x1, double y1, double x2, double y2, double x, double y, double radius){
        double dx = x2 - x1;
        double dy = y2 - y1;
        double fx = x1 - x;
        double fy = y1 - y;

        double a = dx * dx + dy * dy;
        double b = 2 * (fx * dx + fy * dy);
        double c = (fx * fx + fy * fy) - radius * radius;

        double discriminant = b * b - 4 * a * c;

        double t = -b / (2 * a);
        double ansX = x1 + t * dx;
        double ansY = y1 + t * dy;
        return new Point2D(ansX, ansY);
    }
    default boolean isPointInsideSquare(double x1, double y1, double sideLength, double px, double py) {
        double x2 = x1 + sideLength;
        double y2 = y1 + sideLength;

        return px >= x1 && px <= x2 && py >= y1 && py <= y2;
    }
}
