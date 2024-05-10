package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.SquareView;
import game.windowkill.view.characterView.TriangleView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class SquareModel extends Entity {
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
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();
        //TODO
        getVertices().clear();
        Point2D dLeft = Utils.addVector(new Point2D(0, 20), getPosition());
        Point2D dRight = Utils.addVector(new Point2D(20, 20), getPosition());
        Point2D uRight = Utils.addVector(new Point2D(0, 0), getPosition());
        Point2D uLeft = Utils.addVector(new Point2D(20, 0), getPosition());
        getVertices().add(uLeft);
        getVertices().add(uRight);
        getVertices().add(dRight);
        getVertices().add(dLeft);

    }

    public boolean isPointInsideSquare(double x1, double y1, double sideLength, double px, double py) {
        double x2 = x1 + sideLength;
        double y2 = y1 + sideLength;

        return px >= x1 && px <= x2 && py >= y1 && py <= y2;
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
            return collision(p1) || collision(p2) || collision(p3) || collision(p4);
        }
        TriangleModel triangleModel = (TriangleModel) entity;
        boolean b1, b2, b3;
        b1 = collision(triangleModel.getVertices().get(0));
        b2 = collision(triangleModel.getVertices().get(1));
        b3 = collision(triangleModel.getVertices().get(2));
        if(b1 || b2 || b3)return true;
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
            if(collision(p1))
                return p1;
            if(collision(p2))
                return p2;
            if(collision(p3))
                return p3;
            if(collision(p4))
                return p4;
        }
        TriangleModel triangleModel = (TriangleModel) entity;
        boolean b1, b2, b3;
        b1 = collision(triangleModel.getVertices().get(0));
        b2 = collision(triangleModel.getVertices().get(1));
        b3 = collision(triangleModel.getVertices().get(2));
        if(b1)return triangleModel.getVertices().get(0);
        if(b2)return triangleModel.getVertices().get(1);
        if(b3)return triangleModel.getVertices().get(2);

        return null;
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
