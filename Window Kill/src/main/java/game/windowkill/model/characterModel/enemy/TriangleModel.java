package game.windowkill.model.characterModel.enemy;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionTriangleTriangle;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.strategy.Routing;
import game.windowkill.view.characterView.enemy.TriangleView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.TRIANGLE_OUTSIDE;

public class TriangleModel extends Entity implements CollisionTriangleTriangle {
    public static ArrayList<TriangleModel> triangleModels = new ArrayList<>();
    public TriangleModel(double x, double y){
        super(x, y);
        triangleModels.add(this);
        new TriangleView(id, position, direction);
        hp = 15;
        attackHp = 10;
        collectables = 2;
    }

    public void remove(){
        triangleModels.remove(this);
        TriangleView nowTriangleView= null;
        for(TriangleView triangleView : TriangleView.triangleViews)
            if(triangleView.getId().equals(getId()))
                nowTriangleView = triangleView;
        TriangleView.triangleViews.remove(nowTriangleView);
        entities.remove(this);
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();
        //TODO : update here in Update class
        getVertices().clear();

        Point2D left = Utils.addVector(new Point2D(0, TRIANGLE_OUTSIDE), getPosition());
        Point2D right = Utils.addVector(new Point2D(TRIANGLE_OUTSIDE, TRIANGLE_OUTSIDE), getPosition());
        Point2D up = Utils.addVector(new Point2D(TRIANGLE_OUTSIDE/2, 0), getPosition());
        getVertices().add(right);
        getVertices().add(left);
        getVertices().add(up);
        //TODO : make new vector for vertices;
//        Point2D point2D = Utils.multiplyVector()
//        getVertices().add()
    }

    @Override
    public boolean collision(Point2D point2D) {
        Point2D p1 = vertices.get(0);
        Point2D p2 = vertices.get(1);
        Point2D p3 = vertices.get(2);
        return isPointInsideTriangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), point2D.getX(), point2D.getY());
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof EpsilonModel)
            return entity.checkIntersect(this);
        if(entity instanceof SquareModel){
            return entity.checkIntersect(this);
        }
        if(entity instanceof TriangleModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            return checkTriangleTriangle(p1, p2, p3);
        }
        return false;
    }

    @Override
    public Point2D getIntersect(Entity entity) {
        if(entity instanceof EpsilonModel)
            return entity.getIntersect(this);
        if(entity instanceof SquareModel)
            return entity.getIntersect(this);
        if(entity instanceof TriangleModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            return getTriangleTriangle(p1, p2, p3);
        }
        return null;
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
