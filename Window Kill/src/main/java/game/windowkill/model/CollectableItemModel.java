package game.windowkill.model;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.view.CollectableItemView;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.UUID;

public class CollectableItemModel implements Collidable {
    private Point2D position;
    private double radius;
    private Entity type;
    private String id;
    public static ArrayList<CollectableItemModel> collectableItemModels = new ArrayList<>();

    public CollectableItemModel(Point2D position, double radius, Entity type) {
        this.position = position;
        this.radius = radius;
        this.type = type;
        this.id = UUID.randomUUID().toString();
        collectableItemModels.add(this);
        new CollectableItemView(id, position, radius, type);
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

    @Override
    public boolean collision(Point2D point2D) {
        double disY = getPosition().getY() - point2D.getY();
        double disX = getPosition().getX() - point2D.getX();
        return (disX * disX + disY * disY) <= radius * radius;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        return entity.collision(position);
    }
}
