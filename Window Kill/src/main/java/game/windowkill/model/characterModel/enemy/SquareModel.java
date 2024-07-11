package game.windowkill.model.characterModel.enemy;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareSquare;
import game.windowkill.model.characterModel.collision.normalShape.CollisionSquareTriangle;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.strategy.Routing;
import game.windowkill.view.characterView.enemy.SquareView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.SQUARE_OUTSIDE;

public class SquareModel extends Entity implements CollisionSquareSquare, CollisionSquareTriangle {
    public static ArrayList<SquareModel> squareModels = new ArrayList<>();
    public SquareModel(double x, double y){
        super(x, y);
        squareModels.add(this);
        new SquareView(id, position, direction);
        hp = 10;
        attackHp = 6;
        collectables = 1;
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
        Point2D dLeft = Utils.addVector(new Point2D(0, SQUARE_OUTSIDE), getPosition());
        Point2D dRight = Utils.addVector(new Point2D(SQUARE_OUTSIDE, SQUARE_OUTSIDE), getPosition());
        Point2D uRight = Utils.addVector(new Point2D(0, 0), getPosition());
        Point2D uLeft = Utils.addVector(new Point2D(SQUARE_OUTSIDE, 0), getPosition());
        getVertices().add(uLeft);
        getVertices().add(uRight);
        getVertices().add(dRight);
        getVertices().add(dLeft);

    }



    @Override
    public boolean collision(Point2D point2D) {
        return isPointInsideSquare(getX(), getY(), 20, point2D.getX(), point2D.getY());
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
            checkSquareSquare(p1, p2, p3, p4);
        }
        if(entity instanceof TriangleModel) {
            TriangleModel triangleModel = (TriangleModel) entity;
            Point2D p1 = triangleModel.getVertices().get(0);
            Point2D p2 = triangleModel.getVertices().get(1);
            Point2D p3 = triangleModel.getVertices().get(2);
            checkSquareTriangle(p1, p2, p3);
        }
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
            return getSquareSquare(p1, p2, p3, p4);
        }
        if(entity instanceof TriangleModel) {
            TriangleModel triangleModel = (TriangleModel) entity;
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            return getSquareTriangle(p1, p2, p3);
        }

        return null;
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
