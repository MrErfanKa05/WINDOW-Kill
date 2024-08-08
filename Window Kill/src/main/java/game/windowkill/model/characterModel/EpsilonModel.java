package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.attack.MeleeAttack;
import game.windowkill.model.characterModel.enemy.*;
import game.windowkill.model.characterModel.miniBoss.Barricados;
import game.windowkill.model.characterModel.miniBoss.BlackOrb;
import game.windowkill.model.characterModel.multiScene.LocalMultiScene;
import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.MotionSceneModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareCircle;
import game.windowkill.model.characterModel.collision.normalShape.CollisionTriangleCircle;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.abs;

public class EpsilonModel extends Entity implements CollisionSquareCircle, CollisionTriangleCircle, LocalMultiScene, MeleeAttack {
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

    public synchronized int getXP() {
        return XP;
    }

    public synchronized void setXP(int XP) {
        this.XP = XP;
    }

    SkillState skillState = SkillState.Neutral;

    public SkillState getSkillState() {
        return skillState;
    }

    public void setSkillState(SkillState skillState) {
        this.skillState = skillState;
    }

    @Override
    public void remove() {

    }

    @Override
    public Point2D getPosition() {
        double x = getX();
        double y = getY();
        return new Point2D(x, y);
    }
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
        impactDirection = new Direction(0, 0);
        baseSceneModel = MotionSceneModel.getInstance();
        update();
    }

    public double getRadius() {
        return radius;
    }

    private int verticesNum = 0;

    public void addVertex(){
        verticesNum++;
        createAngles();
    }

    public ArrayList<Double> sin = new ArrayList<>();
    public ArrayList<Double> cos = new ArrayList<>();
    public void createAngles(){
        sin.clear();
        cos.clear();
        for(int i = 0; i < verticesNum; i++){
            sin.add(Math.sin(2 * Math.PI / verticesNum));
            cos.add(Math.cos(2 * Math.PI / verticesNum));
        }
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
//        System.out.println(left + " " + getX() + " " + EPSILON_RADIUS);

        if(abs(point.getX() - EPSILON_RADIUS - left) <= 3){

            position = new Point2D(getX() + 1, getY());
            return;
        }
        if(abs(right - point.getX() - EPSILON_RADIUS) <= 3){
            position = new Point2D(getX() - 1, getY());
            return;
        }
        if(abs(point.getY() - EPSILON_RADIUS - top) <= 3){
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
            return checkTriangleCircle(p1, p2, p3, getX(), getY(), getRadius());
        }
        else if(entity instanceof SquareModel squareModel){
            Point2D p1 = squareModel.getVertices().get(0);
            Point2D p2 = squareModel.getVertices().get(1);
            Point2D p3 = squareModel.getVertices().get(2);
            Point2D p4 = squareModel.getVertices().get(3);
            return checkSquareCircle(p1, p2, p3, p4,
                    getPosition().getX(), getPosition().getY(), getRadius());
        }
        if(entity instanceof Omenoct)
            return entity.checkIntersect(this);
        if(entity instanceof NecroPick)
            return entity.checkIntersect(this);
        if(entity instanceof Archmire)
            return entity.checkIntersect(this);
        if(entity instanceof Wyrm)
            return entity.checkIntersect(this);
        if(entity instanceof Barricados)
            return entity.checkIntersect(this);
        if(entity instanceof BlackOrb)
            return entity.checkIntersect(this);
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
        if(entity instanceof Omenoct)
            return entity.getIntersect(this);
        if(entity instanceof NecroPick)
            return entity.getIntersect(this);
        if(entity instanceof Archmire)
            return entity.getIntersect(this);
        if(entity instanceof Wyrm)
            return entity.getIntersect(this);
        if(entity instanceof BlackOrb || entity instanceof Barricados)
            return entity.getIntersect(this);
        return null;
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();

        vertices.clear();

        for(int i = 0; i < verticesNum; i++){
            double x = radius * cos.get(i) + getX();
            double y = radius * sin.get(i) + getY();
            vertices.add(new Point2D(x, y));
        }

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

            if(l == left && r == right && d == down && t == top)
                continue;

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
