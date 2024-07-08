package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.MotionSceneModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareCircle;
import game.windowkill.model.characterModel.collision.normalShape.CollisionTriangleCircle;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.abs;

public class EpsilonModel extends Entity implements CollisionSquareCircle, CollisionTriangleCircle {
    private static EpsilonModel epsilonModel;
    public static EpsilonModel getInstance(){
        if(epsilonModel == null)epsilonModel = new EpsilonModel();
        return epsilonModel;
    }

    private double radius;
    private int XP = 0;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public SkillState getSkillState() {
        return skillState;
    }

    public void setSkillState(SkillState skillState) {
        this.skillState = skillState;
    }

    @Override
    public Point2D getPosition() {
        double x = getX();
        double y = getY();
        return new Point2D(x, y);
    }

    SkillState skillState = SkillState.Neutral;
    private EpsilonModel() {
        super(SCREEN_WIDTH /2 , SCREEN_HEIGHT /2);
        init();
    }
    public void init(){
        speed = Utils.getEpsilonSpeed();
        radius = EPSILON_RADIUS;
        hp = 100;
        attackHp = 5;
        position = new Point2D(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        update();
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean collision(Point2D point2D) {
        if(getDistance(position, point2D) <= EPSILON_RADIUS)
            return true;
        return false;
    }

    @Override
    public void move(Direction direction) {
        change(direction.getDirectionVector(), speed);
    }

    public void change(Direction direction){
        change(direction.getDirectionVector(), speed);
//        move(direction.getDirectionVector(), speed);
    }
    public void change(Point2D point2D, double speed){
        Point2D movement = Utils.multiplyVector(point2D, speed);
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        movement = Utils.addVector(movement, changePosition);
        Point2D point = Utils.addVector(position, movement);

        double left = MotionSceneModel.getInstance().getLeft();
        double right = MotionSceneModel.getInstance().getRight();
        double top = MotionSceneModel.getInstance().getTop();
        double down = MotionSceneModel.getInstance().getDown();

//        System.out.println(left + " " + point.getX());

        if(abs(point.getX() - left) <= 3){
            position = new Point2D(getX() + 1, getY());
            return;
        }
        if(abs(right - point.getX() - 20) <= 3){
            position = new Point2D(getX() - 1, getY());
            return;
        }
        if(abs(point.getY() - top) <= 3){
            position = new Point2D(getX(), getY() + 1);
            return;
        }
        if(abs(down - point.getY() - 20) <= 3){
            position = new Point2D(getX(), getY() - 1);
            return;
        }
        position = Utils.addVector(position, movement);

    }

    @Override
    public void move(Point2D point2D, double speed){
        Point2D movement = Utils.multiplyVector(point2D, speed);
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        movement = Utils.addVector(movement, changePosition);
        if(MotionSceneModel.getInstance().collision(Utils.addVector(position, movement)))
            return;
        position = Utils.addVector(position, movement);

    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof TriangleModel){
            Point2D p1 = entity.vertices.get(0);
            Point2D p2 = entity.vertices.get(1);
            Point2D p3 = entity.vertices.get(2);
            boolean c = checkTriangleCircle(p1, p2, p3, getX(), getY(), getRadius());
            if(c)hp -= 10;
            return c;
        }
        else if(entity instanceof SquareModel squareModel){
            Point2D p1 = squareModel.getVertices().get(0);
            Point2D p2 = squareModel.getVertices().get(1);
            Point2D p3 = squareModel.getVertices().get(2);
            Point2D p4 = squareModel.getVertices().get(3);
            boolean c = checkSquareCircle(p1, p2, p3, p4,
                    getPosition().getX() + radius, getPosition().getY() + radius, getRadius());
            if(c)hp -= 6;
            return c;
        }
        return false;
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof TriangleModel) {
            Point2D p1 = entity.vertices.get(0);
            Point2D p2 = entity.vertices.get(1);
            Point2D p3 = entity.vertices.get(2);
            return getTriangleCircle(p1, p2, p3, getX(), getY(), getRadius());

        }
        if(entity instanceof SquareModel){
            Point2D p1 = entity.vertices.get(0);
            Point2D p2 = entity.vertices.get(1);
            Point2D p3 = entity.vertices.get(2);
            Point2D p4 = entity.vertices.get(3);
            return getSquareCircle(p1, p2, p3, p4, getX(), getY(), getRadius());
        }
        return null;
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
