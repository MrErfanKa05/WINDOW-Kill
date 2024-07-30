package game.windowkill.model.characterModel.enemy;

import game.windowkill.controller.Utils;
import game.windowkill.model.attack.MeleeAttack;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareSquare;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareTriangle;
import game.windowkill.model.characterModel.miniBoss.Barricados;
import game.windowkill.model.characterModel.miniBoss.BlackOrb;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.property.EnemyOverlaps;
import game.windowkill.model.characterModel.property.EnemyProperty;
import game.windowkill.model.strategy.Routing;
import game.windowkill.view.characterView.enemy.SquareView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.SQUARE_OUTSIDE;

public class SquareModel extends Entity implements CollisionSquareSquare, CollisionSquareTriangle, MeleeAttack {
    public static ArrayList<SquareModel> squareModels = new ArrayList<>();
    public SquareModel(double x, double y){
        super(x, y);
        squareModels.add(this);
        new SquareView(id, position, direction);
        hp = 10;
        attackHp = 6;
        collectables = 1;
        collectableXP = 5;
        enemyOverlaps = EnemyOverlaps.NonHovering;
        enemyProperty = EnemyProperty.NonRigid;
    }

    public void remove(){
        squareModels.remove(this);
        SquareView nowSquareView = null;
        for(SquareView squareView : SquareView.squareViews)
            if(squareView.getId().equals(getId()))
                nowSquareView = squareView;
        SquareView.squareViews.remove(nowSquareView);
        entities.remove(this);
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();
        //TODO
        getVertices().clear();
//        Point2D uLeft = Utils.addVector(new Point2D(0, 0), getPosition());
//        Point2D uRight = Utils.addVector(new Point2D(SQUARE_OUTSIDE, 0), getPosition());
//        Point2D dRight = Utils.addVector(new Point2D(SQUARE_OUTSIDE, SQUARE_OUTSIDE), getPosition());
//        Point2D dLeft = Utils.addVector(new Point2D(0, SQUARE_OUTSIDE), getPosition());
        Point2D uLeft = new Point2D(0 + getX(), 0 + getY());
        Point2D uRight = new Point2D(SQUARE_OUTSIDE + getX(), 0 + getY());
        Point2D dRight = new Point2D(SQUARE_OUTSIDE + getX(), SQUARE_OUTSIDE + getY());
        Point2D dLeft = new Point2D(0 + getX(), SQUARE_OUTSIDE + getY());
        getVertices().add(uLeft);
        getVertices().add(uRight);
        getVertices().add(dRight);
        getVertices().add(dLeft);

    }

    @Override
    public boolean collision(Point2D point2D) {
        return isPointInsideSquare(getX(), getY(), SQUARE_OUTSIDE, point2D.getX(), point2D.getY());
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof EpsilonModel)
            return entity.checkIntersect(this);
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
        if(entity instanceof TriangleModel) {
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D d4 = getVertices().get(3);
            return checkSquareTriangle(this, p1, p2, p3) || checkSquareTriangle(entity, d1, d2, d3, d4);
        }
        if(entity instanceof Omenoct)
            return entity.checkIntersect(this);
        if(entity instanceof NecroPick)
            return entity.checkIntersect(this);
        if(entity instanceof Archmire)
            return entity.checkIntersect(this);
        if(entity instanceof Wyrm)
            return entity.checkIntersect(this);
        if(entity instanceof BlackOrb || entity instanceof Barricados)
            return entity.checkIntersect(this);
        return false;
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof EpsilonModel)
            return entity.getIntersect(this);
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
        if(entity instanceof TriangleModel) {
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
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
