package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.move.Impactable;
import game.windowkill.model.characterModel.move.Movable;
import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.strategy.Routing;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.UUID;

import static java.lang.Math.max;

public abstract class Entity implements Collidable, Movable, Impactable {
    protected int piece = 0;
    protected String id;
    protected Point2D position;
    protected Direction direction = new Direction(0, 0);
    protected Direction impactDirection = new Direction(0, 0);

    public static ArrayList<Entity> entities = new ArrayList<>();

    protected double acceleration = 0;
    protected double speed = Utils.getSpeed();
    protected ArrayList<Point2D> vertices = new ArrayList<>();
    protected int hp, attackHp, rangeAttackHp, collectableXP;
    protected int collectables;
    protected BaseSceneModel baseSceneModel = null;

    public Routing routing = Routing.LocalRouting;

    public Entity(double x, double y){
        id = UUID.randomUUID().toString();
        position = new Point2D(x, y);
//        update();
        entities.add(this);
    }
    public void playAcceleration(Point2D point2D, double acc){
        playAcceleration(new Direction(Utils.relativeLocation(position, point2D)), acc);
    }
    public void playAcceleration(Direction direction, double acc){
        acceleration = acc;
        Direction p1 = new Direction(Utils.multiplyVector(impactDirection.getDirectionVector(), acceleration));
        Direction p2 = new Direction(Utils.multiplyVector(direction.getDirectionVector(), acc));

        impactDirection = new Direction(Utils.addVector(p1.getDirectionVector(), p2.getDirectionVector()));
//        impactDirection = Utils.
//        impactDirection = new Direction(Utils.addVector(new Direction(direction.getDirectionVector()).getDirectionVector(),
//                impactDirection.getDirectionVector()));
    }

    public Direction getImpactDirection() {
        return impactDirection;
    }

    public void setImpactDirection(Direction impactDirection) {
        this.impactDirection = impactDirection;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public void decreaseHearth(int num){
        hp -= num;
        hp = max(hp, 0);
    }
    @Override
    public void impact(){
        Direction movement = new Direction(Utils.multiplyVector(impactDirection.getDirectionVector(), acceleration));
        acceleration -= 2.5;
        acceleration = max(acceleration, 0);
        move(movement);
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

    public abstract void update();
    @Override
    public void move(Point2D point2D) {
        move(point2D, speed);
    }

    public double getAcceleration() {
        return acceleration;
    }

    @Override
    public void move(Point2D point2D, double speed) {
        Point2D movement = Utils.multiplyVector(point2D, speed);
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        movement = Utils.addVector(movement, changePosition);
//        movement = Utils.addVector(movement, Utils.multiplyVector(impactDirection.getDirectionVector(), accelerationMove))
        position = Utils.addVector(position, movement);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackHp() {
        return attackHp;
    }

    public void setAttackHp(int attackHp) {
        this.attackHp = attackHp;
    }

    public int getRangeAttackHp() {
        return rangeAttackHp;
    }

    public int getCollectableXP() {
        return collectableXP;
    }

    public int getCollectables() {
        return collectables;
    }

    public void setRangeAttackHp(int rangeAttackHp) {
        this.rangeAttackHp = rangeAttackHp;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setBaseSceneModel(BaseSceneModel baseSceneModel) {
        this.baseSceneModel = baseSceneModel;
    }

    public BaseSceneModel getBaseSceneModel() {
        return baseSceneModel;
    }

    public void setCollectables(int collectables) {
        this.collectables = collectables;
    }
}
