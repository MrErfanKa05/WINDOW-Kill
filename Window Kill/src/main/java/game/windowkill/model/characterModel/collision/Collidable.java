package game.windowkill.model.characterModel.collision;

import game.windowkill.model.characterModel.Entity;
import javafx.geometry.Point2D;

public interface Collidable {
    boolean collision(Point2D point2D);
    boolean checkIntersect(Entity entity);
    Point2D getIntersect(Entity entity);

}
