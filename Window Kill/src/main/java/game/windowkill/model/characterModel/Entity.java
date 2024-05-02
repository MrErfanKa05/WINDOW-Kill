package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.collision.ModelInitializable;
import javafx.geometry.Point2D;

import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Entity implements Collidable, ModelInitializable {
    private int piece = 0;
    private String id;
    private Point2D position;
    private Point2D nextPosition = new Point2D(0, 0);
    private double speed = Utils.getSpeed();
    ArrayList<Point2D> vertices = new ArrayList<>();

    public Entity(double x, double y){
        id = UUID.randomUUID().toString();
        position = new Point2D(x, y);
        Initialize();
    }
    public double getX(){
        return position.getX();
    }
    public double getY(){
        return position.getY();
    }
    public double getDistance(Point2D point2D){
        return Math.sqrt(point2D.getX() * getX() + point2D.getY() * getY());
    }

}
