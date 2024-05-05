package game.windowkill.model;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.view.BulletView;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.UUID;

import static game.windowkill.controller.Constant.BULLET_RADIUS;
import static game.windowkill.controller.Constant.BULLET_SPEED;

public class BulletModel implements Collidable {
    public static ArrayList<BulletModel> bulletModels = new ArrayList<>();

    private String id;
    private double radius = BULLET_RADIUS;
    private double speed = BULLET_SPEED;
    private Point2D position;
    public BulletModel(double x, double y){
        position = new Point2D(x, y);
        bulletModels.add(this);
        id = UUID.randomUUID().toString();
        new BulletView(id, position);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean collision(Point2D point2D) {
        return (point2D.getX() - position.getX()) * (point2D.getX() - position.getX()) +
                (position.getY() - point2D.getY()) * (point2D.getY() - position.getY()) <= radius * radius;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        //TODO : make it with entity collision
        return false;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
