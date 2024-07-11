package game.windowkill.model.sceneModel;

import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.UUID;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class BaseSceneModel implements SceneCollidable {
    public static ArrayList<BaseSceneModel> baseSceneModels = new ArrayList<>();

    protected Direction direction;
    protected double top, left, right, down;
    protected double topAcceleration = 0, downAcceleration = 0, rightAcceleration = 0, leftAcceleration = 0;
    protected boolean goTop = true, goLeft = true, goRight = true, goDown = true;
    public double width, height;

    public static ArrayList<? extends BaseSceneModel> baseSceneModels = new ArrayList<>();
    protected String id;

    public abstract void reset();

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

    protected double changeTime = CHANGE_TIME;
    protected double changeFrame = CHANGE_FRAME;

    public double getChangeTime() {
        return changeTime;
    }

    public double getChangeFrame() {
        return changeFrame;
    }

    public void change(double x, double y){
        if(x > 0)rightAcceleration = ACCELERATION;
        if(x < 0)leftAcceleration = ACCELERATION;
        if(y > 0)downAcceleration = ACCELERATION;
        if(y < 0)topAcceleration = ACCELERATION;
    }

    public void update(){
        changeScene(this);
    }

    public BaseSceneModel() {
        id = UUID.randomUUID().toString();
        baseSceneModels.add(this);
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
        double nowAcceleration = min(acceleration + 2, ACCELERATION - acceleration) + 2;
        left -= 1 * nowAcceleration;
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
}
