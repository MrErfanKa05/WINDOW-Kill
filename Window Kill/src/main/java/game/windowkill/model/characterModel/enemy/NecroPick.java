package game.windowkill.model.characterModel.enemy;

import game.windowkill.controller.Utils;
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
import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.NonIsoNonRigidScene;
import game.windowkill.view.characterView.enemy.NecropickView;
import game.windowkill.view.characterView.enemy.SquareView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.BIG_ENEMY_OUTSIDE;

public class NecroPick extends Entity implements CollisionSquareSquare, CollisionSquareTriangle, CollisionSquareCircle {
    public static ArrayList<NecroPick> necroPicks = new ArrayList<>();
    public NecroPick(double x, double y) {
        super(x, y);
        necroPicks.add(this);
        hp = 10;
        attackHp = 0;
        collectables = 4;
        rangeAttackHp = 5;
        collectableXP = 2;
        new NecropickView(getId(), getPosition(), getDirection());
        enemyOverlaps = EnemyOverlaps.NonHovering;
        enemyProperty = EnemyProperty.Rigid;
        time = System.currentTimeMillis();
        BaseSceneModel sceneModel = new NonIsoNonRigidScene(getX() - 4, getX() + 4 + BIG_ENEMY_OUTSIDE, getY() - 4, getY() + 4 + BIG_ENEMY_OUTSIDE);
        setBaseSceneModel(sceneModel);
    }

    public boolean onGround;
    public long time = 0;

    public void remove(){
        necroPicks.remove(this);
        entities.remove(this);
        NecropickView view = null;
        for(NecropickView necropickView : NecropickView.necropickViews)
            if(necropickView.getId().equals(getId()))
                view = necropickView;
        NecropickView.necropickViews.remove(view);
        baseSceneModel.remove();
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();

        vertices.clear();
        Point2D uLeft = new Point2D(getX(), getY());
        Point2D uRight = Utils.addVector(new Point2D(0, BIG_ENEMY_OUTSIDE), getPosition());
        Point2D dRight = Utils.addVector(new Point2D(BIG_ENEMY_OUTSIDE, BIG_ENEMY_OUTSIDE), getPosition());
        Point2D dLeft = Utils.addVector(new Point2D(BIG_ENEMY_OUTSIDE, 0), getPosition());
        vertices.add(uRight);
        vertices.add(uLeft);
        vertices.add(dRight);
        vertices.add(dLeft);

        baseSceneModel.set(getX() - 4, getX() + 4 + BIG_ENEMY_OUTSIDE, getY() - 4, getY() + 4 + BIG_ENEMY_OUTSIDE);
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
                    epsilonModel.getPosition().getX(), epsilonModel.getPosition().getY(), epsilonModel.getRadius());
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
            return getSquareCircle(p1, p2, p3, p4, epsilonModel.getX(), epsilonModel.getY(), epsilonModel.getRadius());
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
