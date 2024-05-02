package game.windowkill.model.characterModel;

import game.windowkill.model.characterModel.collision.Collidable;
import javafx.geometry.Point2D;

public class BulletModel implements Collidable {

    private Point2D position;
    public BulletModel(double x, double y){
        position = new Point2D(x, y);
    }
    @Override
    public boolean collision(Point2D point2D) {
        return false;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        return false;
    }
}
