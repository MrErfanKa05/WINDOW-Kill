package game.windowkill.view;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class CollectableItemView{
    private Point2D position;
    private double radius;
    private Entity type;
    private String id;
    public static ArrayList<CollectableItemView> collectableItemViews = new ArrayList<>();

    public CollectableItemView(String id, Point2D position, double radius, Entity type) {
        this.position = position;
        this.radius = radius;
        this.type = type;
        this.id = id;
        collectableItemViews.add(this);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Entity getType() {
        return type;
    }

    public void setType(Entity type) {
        this.type = type;
    }
}
