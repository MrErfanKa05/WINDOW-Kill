package game.windowkill.model;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.move.Direction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MotionSceneModel implements Collidable {
    private Direction direction;
    private double top, left, right, down;
    private double topAcceleration = 0, downAcceleration = 0, rightAcceleration = 0, leftAcceleration = 0;
    private boolean goTop = false, goLeft = false, goRight = false, goDown = false;

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

    private double changeTime = 0.5;
    private double changeFrame = 0.5;

    private Timeline move = new Timeline(new KeyFrame(Duration.millis(MODEL_UPDATE_TIME * 1400), actionEvent -> {
//        System.out.println(MODEL_UPDATE_TIME + " ");
//        setDown(downAcceleration);
//        setLeft(leftAcceleration);
//        setRight(rightAcceleration);
//        setUp(topAcceleration);
//
//        downAcceleration = min(0, downAcceleration - changeTime);
//        leftAcceleration = min(0, leftAcceleration - changeTime);
//        rightAcceleration = min(0, rightAcceleration - changeTime);
//        topAcceleration = min(0, topAcceleration - changeTime);

        if(right - left <= 202){
            right += changeFrame;
            left -= changeFrame;
//            rightAcceleration = 0;
//            leftAcceleration = 0;
        }
        if(down - top <= 202){
            top -= changeFrame;
            down += changeFrame;
//            topAcceleration = 0;
//            downAcceleration = 0;
        }
        down -= changeFrame;
        top += changeFrame;
        right -= changeFrame;
        left += changeFrame;

        top = max(0, top);
        left = max(0, left);
        down = min(SCREEN_HEIGHT, down);
        right = min(SCREEN_WIDTH, right);
    }));
    private Timeline moveAfterIntersect = new Timeline(new KeyFrame(Duration.millis(MODEL_UPDATE_TIME * 1000 * 2), actionEvent -> {
//        System.out.println(rightAcceleration + "sad");
//        if(right - left <= 202){
////            right++;
////            left--;
//            rightAcceleration = 0;
//            leftAcceleration = 0;
//        }
//        if(down - top <= 202){
////            top--;
////            down++;
//            topAcceleration = 0;
//            downAcceleration = 0;
//        }
        if(right + rightAcceleration - left + leftAcceleration<= 202){
//            right++;
//            left--;
            rightAcceleration = 0;
            leftAcceleration = 0;
        }
        if(down + downAcceleration - top + topAcceleration <= 202){
//            top--;
//            down++;
            topAcceleration = 0;
            downAcceleration = 0;
        }

        if(downAcceleration > 0)
            setDown(downAcceleration);
        if(leftAcceleration > 0)
            setLeft(leftAcceleration);
        if(rightAcceleration > 0)
            setRight(rightAcceleration);
        if(topAcceleration > 0)
            setUp(topAcceleration);

        downAcceleration = max(0, downAcceleration - changeTime);
        leftAcceleration = max(0, leftAcceleration - changeTime);
        rightAcceleration = max(0, rightAcceleration - changeTime);
        topAcceleration = max(0, topAcceleration - changeTime);

        top = max(0, top);
        left = max(0, left);
        down = min(SCREEN_HEIGHT, down);
        right = min(SCREEN_WIDTH, right);

    }));
    private void setUp(double acceleration){
        double nowAcceleration = min(acceleration, ACCELERATION - acceleration) + 2;
        top -= 1 * nowAcceleration;
    }
    private void setDown(double acceleration){
        double nowAcceleration = min(acceleration, ACCELERATION - acceleration) + 2;
        down += 1 * nowAcceleration;
    }
    private void setLeft(double acceleration){
        double nowAcceleration = min(acceleration + 2, ACCELERATION - acceleration) + 2;
        left -= 1 * nowAcceleration;
    }
    private void setRight(double acceleration){
        double nowAcceleration = min(acceleration, ACCELERATION - acceleration) + 2;
        right += 1 * nowAcceleration;
    }
    private static MotionSceneModel model;
    public static MotionSceneModel getInstance(){
        if(model == null) model = new MotionSceneModel();
        return model;
    }
    private MotionSceneModel(){
        direction = new Direction(0, 0);

        top = SCREEN_HEIGHT/2 - MAX_HEIGHT /2;
        down = SCREEN_HEIGHT/2 + MAX_HEIGHT /2;
        right = SCREEN_WIDTH/2 + MAX_WIDTH /2;
        left = SCREEN_WIDTH/2 - MAX_WIDTH /2;

        moveAfterIntersect.setCycleCount(Animation.INDEFINITE);
        moveAfterIntersect.play();
        move.setCycleCount(Animation.INDEFINITE);
        move.play();
    }

    public void change(double x, double y){
        if(x > 0)rightAcceleration = ACCELERATION;
        if(x < 0)leftAcceleration = ACCELERATION;
        if(y > 0)downAcceleration = ACCELERATION;
        if(y < 0)topAcceleration = ACCELERATION;
    }
    private boolean checkIntersect(Object o){
        if(o instanceof Entity)
            return checkIntersect((Entity) o);
        o = (BulletModel) o;
        return ((BulletModel) o).collision(new Point2D(left, ((BulletModel) o).getPosition().getY())) ||
                ((BulletModel) o).collision(new Point2D(right, ((BulletModel) o).getPosition().getY())) ||
                ((BulletModel) o).collision(new Point2D(((BulletModel) o).getPosition().getX(), top)) ||
                ((BulletModel) o).collision(new Point2D(((BulletModel) o).getPosition().getX(), down));
    }
    @Override
    public boolean collision(Point2D point2D) {
        if(point2D.getY() - top <= 1 ||
                down - point2D.getY() <= 1 ||
                point2D.getX() - left <= 1 ||
                right - point2D.getX() <= 1)
            return true;
        return false;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        return entity.collision(new Point2D(entity.getPosition().getX(), top)) ||
                entity.collision(new Point2D(entity.getPosition().getX(), down)) ||
                entity.collision(new Point2D(left, entity.getPosition().getY())) ||
                entity.collision(new Point2D(right, entity.getPosition().getY()));
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        Point2D p1 = new Point2D(entity.getPosition().getX(), top);
        Point2D p2 = new Point2D(entity.getPosition().getX(), down);
        Point2D p3 = new Point2D(right, entity.getPosition().getY());
        Point2D p4 = new Point2D(left, entity.getPosition().getY());
        if(entity.collision(p1))
            return p1;
        if(entity.collision(p2))
            return p2;
        if(entity.collision(p3))
            return p3;
        if(entity.collision(p4))
            return p4;

        return null;
    }
}
//TODO : Check intersect with board