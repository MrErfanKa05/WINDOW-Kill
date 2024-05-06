package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.MotionSceneModel;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.*;

public class EpsilonModel extends Entity{
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

    SkillState skillState = SkillState.Neutral;
    private EpsilonModel() {
        super(SCREEN_WIDTH /2 , SCREEN_HEIGHT /2);
        speed = Utils.getEpsilonSpeed();
        radius = EPSILON_RADIUS;
        hp = 100;
        attackHp = 5;
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
    public boolean checkIntersectionSquare(double squareTopLeftX, double squareTopLeftY,
                                           double squareBottomRightX, double squareBottomRightY,
                                           double circleCenterX, double circleCenterY, double radius) {
        double nearestX = Math.max(squareTopLeftX, Math.min(circleCenterX, squareBottomRightX));
        double nearestY = Math.max(squareTopLeftY, Math.min(circleCenterY, squareBottomRightY));

        double deltaX = nearestX - circleCenterX;
        double deltaY = nearestY - circleCenterY;

        return (deltaX * deltaX + deltaY * deltaY) < (radius * radius);
    }
    public boolean checkPointInsideCircle(double x1, double y1, double x2, double y2){
        Point2D point = pointInsideCircle(x1, y1, x2, y2);
        return getDistance(point, new Point2D(getX(), getY())) <= radius;
    }
    public Point2D pointInsideCircle(double x1, double y1, double x2, double y2){
        double lineLengthX = x2 - x1;
        double lineLengthY = y2 - y1;

        double t = ((getX() - x1) * lineLengthX + (getY() - y1) * lineLengthY) /
                (lineLengthX * lineLengthX + lineLengthY * lineLengthY);
        t = Math.max(0, Math.min(t, 1));

        double nearestX = x1 + t * lineLengthX;
        double nearestY = y1 + t * lineLengthY;

        return new Point2D(nearestX, nearestY);
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
            return checkPointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY()) &&
                    checkPointInsideCircle(p1.getX(), p1.getY(), p3.getX(), p3.getY()) &&
                    checkPointInsideCircle(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        }
        else if(entity instanceof SquareModel squareModel){
            return checkIntersectionSquare(squareModel.getPosition().getX(), squareModel.getPosition().getY(),
                    20 + squareModel.getPosition().getX(), 20 + squareModel.getPosition().getY(),
                    getPosition().getX(), getPosition().getY(), getRadius());
        }
        return false;
    }

    public double pointLineDistance(double x1, double y1, double x2, double y2, double px, double py) {
        double norm = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return Math.abs((px - x1) * (y2 - y1) - (py - y1) * (x2 - x1)) / norm;
    }
    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof TriangleModel) {
            Point2D p1 = entity.vertices.get(0);
            Point2D p2 = entity.vertices.get(1);
            Point2D p3 = entity.vertices.get(2);
            Point2D ans = null;
            Point2D f1 = pointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            Point2D f2 = pointInsideCircle(p3.getX(), p3.getY(), p2.getX(), p2.getY());
            Point2D f3 = pointInsideCircle(p1.getX(), p1.getY(), p3.getX(), p3.getY());
            double dis1 = pointLineDistance(p1.getX(), p1.getY(), p2.getX(), p2.getY(), getPosition().getX(), getPosition().getY());
            double dis2 = pointLineDistance(p3.getX(), p3.getY(), p2.getX(), p2.getY(), getPosition().getX(), getPosition().getY());
            double dis3 = pointLineDistance(p1.getX(), p1.getY(), p3.getX(), p3.getY(), getPosition().getX(), getPosition().getY());
            if(dis3 <= radius)ans = f3;
            if(dis2 <= radius)ans = f2;
            if(dis1 <= radius)ans = f1;
            return ans;

        }
        if(entity instanceof SquareModel){
            Point2D p1 = entity.vertices.get(0);
            Point2D p2 = entity.vertices.get(1);
            Point2D p3 = entity.vertices.get(2);
            Point2D p4 = entity.vertices.get(3);
            Point2D ans = null;
            Point2D f1 = pointInsideCircle(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            Point2D f2 = pointInsideCircle(p3.getX(), p3.getY(), p2.getX(), p2.getY());
            Point2D f3 = pointInsideCircle(p1.getX(), p1.getY(), p4.getX(), p4.getY());
            Point2D f4 = pointInsideCircle(p4.getX(), p4.getY(), p3.getX(), p3.getY());
            double dis1 = pointLineDistance(p1.getX(), p1.getY(), p2.getX(), p2.getY(), getPosition().getX(), getPosition().getY());
            double dis2 = pointLineDistance(p3.getX(), p3.getY(), p2.getX(), p2.getY(), getPosition().getX(), getPosition().getY());
            double dis3 = pointLineDistance(p1.getX(), p1.getY(), p4.getX(), p4.getY(), getPosition().getX(), getPosition().getY());
            double dis4 = pointLineDistance(p4.getX(), p4.getY(), p3.getX(), p3.getY(), getPosition().getX(), getPosition().getY());
            if(dis3 <= radius) ans = f3;
            if(dis2 <= radius) ans = f2;
            if(dis1 <= radius) ans = f1;
            if(dis4 <= radius) ans = f4;
            return ans;
        }
        return null;
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }

    public enum SkillState {
        Neutral, WritOfAres, WritOfAceso, WritOfProteus
    }
}
