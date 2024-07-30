package game.windowkill.model.characterModel.enemy;

import game.windowkill.controller.Utils;
import game.windowkill.model.attack.AreaOfEffect;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareCircle;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareSquare;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareTriangle;
import game.windowkill.model.characterModel.miniBoss.Barricados;
import game.windowkill.model.characterModel.miniBoss.BlackOrb;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.property.EnemyOverlaps;
import game.windowkill.model.characterModel.property.EnemyProperty;
import game.windowkill.view.characterView.enemy.ArchmireView;
import game.windowkill.view.characterView.enemy.SquareView;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static game.windowkill.controller.Constant.BIG_ENEMY_OUTSIDE;
import static game.windowkill.controller.Constant.SQUARE_OUTSIDE;
import static java.lang.Math.min;

public class Archmire extends Entity implements CollisionSquareTriangle, CollisionSquareSquare, CollisionSquareCircle, AreaOfEffect {
    public static ArrayList<Archmire> archmires = new ArrayList<>();
    public static Map<Entity, Long> drownTimes = new HashMap<>();
    public Archmire(double x, double y) {
        super(x, y);
        archmires.add(this);
        new SquareView(id, position, direction);
        hp = 30;
        attackHp = 0;
        collectables = 5;
        collectableXP = 6;
        new ArchmireView(getId(), getPosition(), getDirection());
        enemyOverlaps = EnemyOverlaps.Hovering;
        enemyProperty = EnemyProperty.NonRigid;
    }

    public void drown(Entity entity){
        int num = 0;
        int type = 10;
        for(Point2D point2D : entity.getVertices()){
            if(checkAreaEffect(point2D)) {
                num++;
                type = min(type, getAreaEffect(point2D));
            }
        }
        if(num == 0) {
            drownTimes.remove(entity);
            return;
        }
        if(drownTimes.containsKey(entity))
            if(System.currentTimeMillis() - drownTimes.get(entity) < 1000)
                return;
        if(!drownTimes.containsKey(entity))
            drownTimes.put(entity, System.currentTimeMillis());
        if(num == entity.getVertices().size()){
            entity.decreaseHearth(type);
        }

    }

    public void remove(){
        archmires.remove(this);
        entities.remove(this);
        ArchmireView view = null;
        for(ArchmireView archmireView : ArchmireView.archmireViews)
            if(archmireView.getId().equals(getId()))
                view = archmireView;
        ArchmireView.archmireViews.remove(view);
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();

        vertices.clear();
        Point2D uLeft = new Point2D(getX(), getY());
        Point2D uRight = Utils.addVector(new Point2D(0, SQUARE_OUTSIDE), getPosition());
        Point2D dRight = Utils.addVector(new Point2D(SQUARE_OUTSIDE, SQUARE_OUTSIDE), getPosition());
        Point2D dLeft = Utils.addVector(new Point2D(SQUARE_OUTSIDE, 0), getPosition());
        vertices.add(uRight);
        vertices.add(uLeft);
        vertices.add(dRight);
        vertices.add(dLeft);
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
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D d4 = getVertices().get(3);
            return checkSquareTriangle(this, p1, p2, p3) || checkSquareTriangle(entity, d1, d2, d3, d4);
        }
        if(entity instanceof SquareModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D d4 = getVertices().get(3);
            return checkSquareSquare(this, p1, p2, p3, p4) || checkSquareSquare(entity, d1, d2, d3, d4);
        }
        if(entity instanceof EpsilonModel epsilonModel){
            Point2D p1 = getVertices().get(0);
            Point2D p2 = getVertices().get(1);
            Point2D p3 = getVertices().get(2);
            Point2D p4 = getVertices().get(3);
            return checkSquareCircle(p1, p2, p3, p4,
                    getPosition().getX(), getPosition().getY(), epsilonModel.getRadius());
        }
        if(entity instanceof BlackOrb){
            return entity.checkIntersect(this);
        }
        if(entity instanceof NecroPick || entity instanceof Archmire || entity instanceof Wyrm || entity instanceof Barricados ||
                entity instanceof Omenoct){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D d4 = getVertices().get(3);
            return checkSquareSquare(this, p1, p2, p3, p4) || checkSquareSquare(entity, d1, d2, d3, d4);
        }
        return false;
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof TriangleModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D d4 = getVertices().get(3);
            Point2D point2D1 = getSquareTriangle(this, p1, p2, p3);
            Point2D point2D2 = getSquareTriangle(entity, d1, d2, d3, d4);
            Point2D ans = (point2D1 == null ? point2D2 : point2D1);
            return ans;
        }
        if(entity instanceof SquareModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D d4 = getVertices().get(3);
            Point2D point2D1 = getSquareSquare(this, p1, p2, p3, p4);
            Point2D point2D2 = getSquareSquare(entity, d1, d2, d3, d4);
            Point2D ans = (point2D1 == null ? point2D2 : point2D1);
            return ans;
        }
        if(entity instanceof EpsilonModel epsilonModel){
            Point2D p1 = vertices.get(0);
            Point2D p2 = vertices.get(1);
            Point2D p3 = vertices.get(2);
            Point2D p4 = vertices.get(3);
            return getSquareCircle(p1, p2, p3, p4, getX(), getY(), epsilonModel.getRadius());
        }
        if(entity instanceof BlackOrb){
            return entity.getIntersect(this);
        }
        if(entity instanceof NecroPick || entity instanceof Archmire || entity instanceof Wyrm || entity instanceof Barricados ||
                entity instanceof Omenoct){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D p4 = entity.getVertices().get(3);
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D d4 = getVertices().get(3);
            Point2D point2D1 = getSquareSquare(this, p1, p2, p3, p4);
            Point2D point2D2 = getSquareSquare(entity, d1, d2, d3, d4);
            Point2D ans = (point2D1 == null ? point2D2 : point2D1);
            return ans;
        }
        return null;
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }

}
