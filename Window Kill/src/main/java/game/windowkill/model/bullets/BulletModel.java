package game.windowkill.model.bullets;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.move.Movable;
import javafx.geometry.Point2D;

import java.util.UUID;

import static game.windowkill.controller.Constant.BULLET_RADIUS;
import static game.windowkill.controller.Constant.BULLET_SPEED;

public abstract class BulletModel implements Collidable, Movable {

    protected String id;
    protected double radius = BULLET_RADIUS;
    protected double speed = BULLET_SPEED;
    protected Direction direction = new Direction(0, 0);
    protected Point2D position;

    public BulletModel(double x, double y){
        position = new Point2D(x, y);
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void update(){
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
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

    @Override
    public Point2D getIntersect(Entity entity) {
        return null;
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

    @Override
    public void move(Point2D point2D) {
        move(point2D, speed);
    }

    @Override
    public void move(Point2D point2D, double speed) {
        Point2D movement = Utils.multiplyVector(point2D, speed);
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        movement = Utils.addVector(movement, changePosition);
        position = Utils.addVector(position, movement);
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
