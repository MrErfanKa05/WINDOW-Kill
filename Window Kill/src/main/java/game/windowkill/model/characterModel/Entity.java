package game.windowkill.model.characterModel;

import game.windowkill.controller.Constant;
import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.move.Movable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.UUID;

import static game.windowkill.controller.Constant.ACCELERATION;
import static game.windowkill.controller.Constant.ACCELERATION_TIME;

public abstract class Entity implements Collidable, Movable {
    int piece = 0;
    String id;
    Point2D position;
    Direction direction = new Direction(0, 0);
    Direction impactDirection = new Direction(0, 0);
    double acceleration = 0;
    double speed = Utils.getSpeed();
    ArrayList<Point2D> vertices = new ArrayList<>();

    private Timeline accelerationMove = new Timeline(new KeyFrame(Duration.millis(ACCELERATION_TIME / ACCELERATION), actionEvent -> {
        move(Utils.addVector(direction.getDirectionVector(), Utils.multiplyVector(impactDirection.getDirectionVector(), acceleration)));
        acceleration -= ACCELERATION_TIME/ACCELERATION;
    }));
    public Entity(double x, double y){
        id = UUID.randomUUID().toString();
        position = new Point2D(x, y);
        update();
    }
    public void playAcceleration(Point2D point2D){
        playAcceleration(new Direction(point2D));
    }
    public void playAcceleration(Direction direction){
        acceleration = ACCELERATION;
        impactDirection = new Direction(Utils.addVector(direction.getDirectionVector(), impactDirection.getDirectionVector()));
        accelerationMove.stop();
        accelerationMove.setCycleCount((int) (ACCELERATION/ACCELERATION_TIME));
        accelerationMove.play();
    }

    public String getId() {
        return id;
    }

    public Point2D getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
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
