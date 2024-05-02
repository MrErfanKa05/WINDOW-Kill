package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.move.Movable;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Entity implements Collidable, Movable {
    int piece = 0;
    String id;
    Point2D position;
    Direction direction = new Direction(0, 0);
    double speed = Utils.getSpeed();
    ArrayList<Point2D> vertices = new ArrayList<>();

    public Entity(double x, double y){
        id = UUID.randomUUID().toString();
        position = new Point2D(x, y);
        update();
    }

    public ArrayList<Point2D> getVertices() {
        return vertices;
    }

    public double getX(){
        return position.getX();
    }
    public double getY(){
        return position.getY();
    }
    public double getDistance(Point2D point1, Point2D point2){
        return Math.sqrt(point2.getX() * point1.getX() + point2.getY() * point1.getY());
    }
    public abstract void update();

}
