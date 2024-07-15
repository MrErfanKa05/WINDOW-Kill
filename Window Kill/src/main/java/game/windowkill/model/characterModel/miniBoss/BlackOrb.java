package game.windowkill.model.characterModel.miniBoss;

import game.windowkill.controller.Constant;
import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionCircleCircle;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareCircle;
import game.windowkill.model.characterModel.collision.normalShape.CollisionTriangleCircle;
import game.windowkill.model.characterModel.enemy.*;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.enemy.SquareView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.BIG_ENEMY_OUTSIDE;
import static game.windowkill.controller.Constant.BLACKORB_RADIUS;

public class BlackOrb extends Entity implements CollisionSquareCircle, CollisionTriangleCircle, CollisionCircleCircle {
    public static ArrayList<BlackOrb> blackOrbs = new ArrayList<>();
    public BlackOrb(double x, double y) {
        super(x, y);
        blackOrbs.add(this);
        new SquareView(id, position, direction);
        hp = 30;
        attackHp = 0;
        collectables = 5;
        collectableXP = 30;

        //todo : two type
    }

    public void remove(){
        blackOrbs.remove(this);
        entities.remove(this);
        //todo : remove for view
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();
    }
    @Override
    public boolean collision(Point2D point2D) {
        return isPointInsideSquare(getX(), getY(), BIG_ENEMY_OUTSIDE, point2D.getX(), point2D.getY());
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof TriangleModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            return checkTriangleCircle(p1, p2, p3, getX(), getY(), BLACKORB_RADIUS);
        }
        if(entity instanceof SquareModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            return checkSquareCircle(p1, p2, p3, p4, getX(), getY(), BLACKORB_RADIUS);
        }
        if(entity instanceof EpsilonModel epsilonModel){
            return checkCircleCircle(getX(), getY(), BLACKORB_RADIUS, entity.getX(), entity.getY(), epsilonModel.getRadius());
        }
        if(entity instanceof BlackOrb){
            return checkCircleCircle(getX(), getY(), BLACKORB_RADIUS, entity.getX(), entity.getY(), BLACKORB_RADIUS);
        }
        if(entity instanceof NecroPick || entity instanceof Archmire || entity instanceof Wyrm || entity instanceof Barricados ||
                entity instanceof Omenoct){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            return checkSquareCircle(p1, p2, p3, p4, getX(), getY(), BLACKORB_RADIUS);
        }
        return false;
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof TriangleModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            return getTriangleCircle(p1, p2, p3, getX(), getY(), BLACKORB_RADIUS);
        }
        if(entity instanceof SquareModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            return getSquareCircle(p1, p2, p3, p4, getX(), getY(), BLACKORB_RADIUS);
        }
        if(entity instanceof EpsilonModel epsilonModel){
            return getCircleCircle(getX(), getY(), BLACKORB_RADIUS, entity.getX(), entity.getY(), BLACKORB_RADIUS);
        }
        if(entity instanceof BlackOrb){
            return getCircleCircle(getX(), getY(), BLACKORB_RADIUS, entity.getX(), entity.getY(), BLACKORB_RADIUS);
        }
        if(entity instanceof NecroPick || entity instanceof Archmire || entity instanceof Wyrm || entity instanceof Barricados ||
                entity instanceof Omenoct){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            return getSquareCircle(p1, p2, p3, p4, getX(), getY(), BLACKORB_RADIUS);
        }
        return null;
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
