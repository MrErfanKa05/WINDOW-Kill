package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.multiScene.LocalMultiScene;
import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.MotionSceneModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareCircle;
import game.windowkill.model.characterModel.collision.normalShape.CollisionTriangleCircle;
import game.windowkill.model.characterModel.enemy.SquareModel;
import game.windowkill.model.characterModel.enemy.TriangleModel;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.abs;

public class EpsilonModel extends Entity implements CollisionSquareCircle, CollisionTriangleCircle, LocalMultiScene {
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
        baseSceneModel = MotionSceneModel.getInstance();
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

        if(abs(point.getX() + EPSILON_RADIUS - left) <= 3){
            position = new Point2D(getX() + 1, getY());
            return;
        }
        if(abs(right - point.getX() - EPSILON_RADIUS) <= 3){
            position = new Point2D(getX() - 1, getY());
            return;
        }
        if(abs(point.getY() + EPSILON_RADIUS - top) <= 3){
            position = new Point2D(getX(), getY() + 1);
            return;
        }
        if(abs(down - point.getY() - EPSILON_RADIUS) <= 3){
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
                    getPosition().getX(), getPosition().getY(), getRadius());
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
        if(isMultiScene()){
            double left = baseSceneModel.getLeft();
            double right = baseSceneModel.getRight();
            double top = baseSceneModel.getTop();
            double down = baseSceneModel.getDown();

            if(isInsideMultiScene(new Point2D(left, top))) {
                baseSceneModel.setGoTop(false);
                baseSceneModel.setGoLeft(false);
            }
            if(isInsideMultiScene(new Point2D(left, down))){
                baseSceneModel.setGoLeft(false);
                baseSceneModel.setGoDown(false);
            }
            if(isInsideMultiScene(new Point2D(right, top))){
                baseSceneModel.setGoRight(false);
                baseSceneModel.setGoTop(false);
            }
            if(isInsideMultiScene(new Point2D(right, down))){
                baseSceneModel.setGoRight(false);
                baseSceneModel.setGoDown(false);
            }


            if(!isInsideMultiScene(new Point2D(left, top))) {
                baseSceneModel.setGoTop(true);
                baseSceneModel.setGoLeft(true);
            }
            if(!isInsideMultiScene(new Point2D(left, down))){
                baseSceneModel.setGoLeft(true);
                baseSceneModel.setGoDown(true);
            }
            if(!isInsideMultiScene(new Point2D(right, top))){
                baseSceneModel.setGoRight(true);
                baseSceneModel.setGoTop(true);
            }
            if(!isInsideMultiScene(new Point2D(right, down))){
                baseSceneModel.setGoRight(true);
                baseSceneModel.setGoDown(true);
            }
        }
        else {
            baseSceneModel.setGoDown(true);
            baseSceneModel.setGoLeft(true);
            baseSceneModel.setGoRight(true);
            baseSceneModel.setGoTop(true);
        }
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }

    @Override
    public boolean isMultiScene() {
        double left = baseSceneModel.getLeft();
        double right = baseSceneModel.getRight();
        double down = baseSceneModel.getDown();
        double top = baseSceneModel.getTop();
        for(BaseSceneModel model : BaseSceneModel.baseSceneModels){
            double l = model.getLeft();
            double r = model.getRight();
            double t = model.getTop();
            double d = model.getDown();

            Point2D p1 = new Point2D(l, t);
            Point2D p2 = new Point2D(l, d);
            Point2D p3 = new Point2D(r, t);
            Point2D p4 = new Point2D(r, d);

            if(baseSceneModel.collision(p1) || baseSceneModel.collision(p2) || baseSceneModel.collision(p3) || baseSceneModel.collision(p4))
                return true;
        }
        return false;
    }

    @Override
    public boolean isInsideMultiScene(Point2D point2D) {
        int num = 0;
        for(BaseSceneModel model : BaseSceneModel.baseSceneModels)
            if(model.collision(point2D))
                num++;
        return (num >= 2);
    }
}
