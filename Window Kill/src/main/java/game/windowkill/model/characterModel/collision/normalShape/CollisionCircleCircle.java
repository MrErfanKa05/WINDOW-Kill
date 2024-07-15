package game.windowkill.model.characterModel.collision.normalShape;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

public interface CollisionCircleCircle extends ModeInitialize {
    default boolean checkCircleCircle(double x1, double y1, double radius1, double x2, double y2, double radius2){
        return (Utils.getDistance(new Point2D(x1, y1), new Point2D(x2, y2)) <= radius1 + radius2);
    }
    default Point2D getCircleCircle(double x1, double y1, double radius1, double x2, double y2, double radius2){
        double distance = getDistance(new Point2D(x1, y1), new Point2D(x2, y2));
        double intersectionX = (radius1 * radius1 - radius2 * radius2 + distance * distance) / (2 * distance);
        double intersectionY = Math.sqrt(radius1 * radius1 - intersectionX * intersectionX);
        return new Point2D(intersectionX, intersectionY);
    }
}
