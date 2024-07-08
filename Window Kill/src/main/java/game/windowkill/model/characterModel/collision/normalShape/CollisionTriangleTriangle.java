package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

public interface CollisionTriangleTriangle extends ModeInitialize {
    default boolean checkTriangleTriangle(Point2D p1, Point2D p2, Point2D p3){
        return collision(p1) || collision(p2) || collision(p3);
    }
    default Point2D getTriangleTriangle(Point2D p1, Point2D p2, Point2D p3){
        if(collision(p1))
            return p1;
        if(collision(p2))
            return p2;
        if(collision(p3))
            return p3;
        return null;
    }
    default double sign(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y) {
        return (p1x - p3x) * (p2y - p3y) - (p2x - p3x) * (p1y - p3y);
    }
    default public boolean isPointInsideTriangle(double x1, double y1, double x2, double y2, double x3, double y3, double px, double py) {
        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(px, py, x1, y1, x2, y2);
        d2 = sign(px, py, x2, y2, x3, y3);
        d3 = sign(px, py, x3, y3, x1, y1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }
}
