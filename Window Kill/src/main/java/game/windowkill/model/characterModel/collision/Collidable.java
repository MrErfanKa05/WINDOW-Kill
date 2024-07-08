package game.windowkill.model.characterModel.collision;

import game.windowkill.model.characterModel.Entity;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;
import static java.lang.Math.abs;

public interface Collidable {
    boolean collision(Point2D point2D);
    boolean checkIntersect(Entity entity);
    Point2D getIntersect(Entity entity);

}
