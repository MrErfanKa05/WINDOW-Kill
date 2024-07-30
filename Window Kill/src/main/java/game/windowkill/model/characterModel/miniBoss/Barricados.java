package game.windowkill.model.characterModel.miniBoss;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareCircle;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareSquare;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareTriangle;
import game.windowkill.model.characterModel.enemy.*;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.enemy.SquareView;
import game.windowkill.view.characterView.miniBoss.BarricadosView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.BIG_ENEMY_OUTSIDE;

public class Barricados extends Entity implements CollisionSquareCircle, CollisionSquareSquare, CollisionSquareTriangle {
    public static ArrayList<Barricados> barricados = new ArrayList<>();
    public Barricados(double x, double y) {
        super(x, y);
        barricados.add(this);
        new SquareView(id, position, direction);
        hp = Integer.MAX_VALUE;
        new BarricadosView(getId(), getPosition(), getDirection());
    }

    public void remove(){
        barricados.remove(this);
        entities.remove(this);
        BarricadosView view = null;
        for(BarricadosView barricadosView : BarricadosView.barricadosViews)
            if(barricadosView.getId().equals(getId()))
                view = barricadosView;
        BarricadosView.barricadosViews.remove(view);
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
