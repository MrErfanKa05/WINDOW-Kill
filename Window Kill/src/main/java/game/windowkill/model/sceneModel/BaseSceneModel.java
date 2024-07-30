package game.windowkill.model.sceneModel;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.scenes.CollisionCircleScene;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.UUID;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class BaseSceneModel implements SceneCollidable, CollisionCircleScene {
    public static ArrayList<BaseSceneModel> baseSceneModels = new ArrayList<>();

    protected Direction direction;
    protected double top = 0, left = 0, right = 0, down = 0;
    protected double topAcceleration = 0, downAcceleration = 0, rightAcceleration = 0, leftAcceleration = 0;
    protected boolean goTop = true, goLeft = true, goRight = true, goDown = true;
    public double width, height;

    protected String id;

    public void set(double left, double right, double top, double down){
        this.left = left;
        this.right = right;
        this.top = top;
        this.down = down;
    }


    public void reset(){};

    public double getLeftAcceleration() {
        return leftAcceleration;
    }

    public void setLeftAcceleration(double leftAcceleration) {
        this.leftAcceleration = leftAcceleration;
    }

    public double getRightAcceleration() {
        return rightAcceleration;
    }

    public void setRightAcceleration(double rightAcceleration) {
        this.rightAcceleration = rightAcceleration;
    }

    public double getDownAcceleration() {
        return downAcceleration;
    }

    public void setDownAcceleration(double downAcceleration) {
        this.downAcceleration = downAcceleration;
    }

    public double getTopAcceleration() {
        return topAcceleration;
    }

    public void setTopAcceleration(double topAcceleration) {
        this.topAcceleration = topAcceleration;
    }

    public double getTop() {
        return top;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getDown() {
        return down;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public void setDown(double down) {
        this.down = down;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public boolean isGoTop() {
        return goTop;
    }

    public void setGoTop(boolean goTop) {
        this.goTop = goTop;
    }

    public boolean isGoLeft() {
        return goLeft;
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    public boolean isGoRight() {
        return goRight;
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }

    public boolean isGoDown() {
        return goDown;
    }

    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    protected static double changeTime = CHANGE_TIME;
    protected static double changeFrame = CHANGE_FRAME;

    public static double getChangeTime() {
        return changeTime;
    }

    public static double getChangeFrame() {
        return changeFrame;
    }

    public static void setChangeTime(double changeTime) {
        BaseSceneModel.changeTime = changeTime;
    }

    public static void setChangeFrame(double changeFrame) {
        BaseSceneModel.changeFrame = changeFrame;
    }

    public void change(double x, double y){
        if(x > 0)rightAcceleration = ACCELERATION;
        if(x < 0)leftAcceleration = ACCELERATION;
        if(y > 0)downAcceleration = ACCELERATION;
        if(y < 0)topAcceleration = ACCELERATION;
    }

    @Deprecated
    public void changeAs(double x, double y){
        //todo : check this
        if(x < left){
            leftAcceleration = -ACCELERATION;
            rightAcceleration = ACCELERATION;
        }
        if(x > right) {
            leftAcceleration = ACCELERATION;
            rightAcceleration = -ACCELERATION;
        }
        if(y < top){
            topAcceleration = -ACCELERATION;
            downAcceleration = ACCELERATION;
        }
        if(y > down){
            topAcceleration = ACCELERATION;
            downAcceleration = -ACCELERATION;
        }
    }

    public void update(){
        changeScene(this);
    }

    public BaseSceneModel() {
        id = UUID.randomUUID().toString();
        direction = new Direction(0, 0);
//        baseSceneModels.add(this);
        reset();
    }

    public String getId() {
        return id;
    }

    public void remove(){
        baseSceneModels.remove(this);
    }

    public void setGoTop(double acceleration){
        double nowAcceleration = min(acceleration, ACCELERATION - acceleration) + 2;
        top -= 1 * nowAcceleration;
    }
    public void setGoDown(double acceleration){
        double nowAcceleration = min(acceleration, ACCELERATION - acceleration) + 2;
        down += 1 * nowAcceleration;
    }
    public void setGoLeft(double acceleration){
        double nowAcceleration = min(acceleration, ACCELERATION - acceleration) + 2;
        left -= 1 * nowAcceleration;
//        System.out.println(left + " " + nowAcceleration);
    }
    public void setGoRight(double acceleration){
        double nowAcceleration = min(acceleration, ACCELERATION - acceleration) + 2;
        right += 1 * nowAcceleration;
    }

    @Override
    public boolean collision(Point2D point2D) {
        if(point2D.getY() - top <= 2 ||
                down - point2D.getY() <= 2 ||
                point2D.getX() - left <= 2 ||
                right - point2D.getX() <= 2)
            return true;
        return false;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof EpsilonModel) {
            return checkCircleScene(entity, top, down, left, right);
        }
        return false;
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof EpsilonModel) {
            Point2D p1 = new Point2D(entity.getPosition().getX(), top);
            Point2D p2 = new Point2D(entity.getPosition().getX(), down);
            Point2D p3 = new Point2D(right, entity.getPosition().getY());
            Point2D p4 = new Point2D(left, entity.getPosition().getY());
            return getCircleScene(entity, p1, p2, p3, p4);
        }

        return null;
    }
}
