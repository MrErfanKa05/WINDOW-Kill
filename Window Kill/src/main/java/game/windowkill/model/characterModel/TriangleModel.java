package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.SquareView;
import game.windowkill.view.characterView.TriangleView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class TriangleModel extends Entity {
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
    }

    @Override
    public void update() {
        Point2D changePosition = Utils.multiplyVector(direction.getDirectionVector(), speed);
        position = Utils.addVector(position, changePosition);
        impact();
        //TODO : update here in Update class
        getVertices().clear();

        Point2D left = Utils.addVector(new Point2D(0, 20), getPosition());
        Point2D right = Utils.addVector(new Point2D(20, 20), getPosition());
        Point2D up = Utils.addVector(new Point2D(10, 0), getPosition());
        getVertices().add(right);
        getVertices().add(left);
        getVertices().add(up);
        //TODO : make new vector for vertices;
//        Point2D point2D = Utils.multiplyVector()
//        getVertices().add()
    }
    private boolean doSegmentsIntersect(Point2D p1, Point2D q1, Point2D p2, Point2D q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && isOnSegment(p1, p2, q1))
            return true;
        if (o2 == 0 && isOnSegment(p1, q2, q1))
            return true;
        if (o3 == 0 && isOnSegment(p2, p1, q2))
            return true;
        if (o4 == 0 && isOnSegment(p2, q1, q2))
            return true;

        return false;
    }

    private int orientation(Point2D p, Point2D q, Point2D r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0) return 0; // Collinear
        return (val > 0) ? 1 : 2; // Clockwise or counterclockwise
    }

    private boolean isOnSegment(Point2D p, Point2D q, Point2D r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
    }

    private double sign(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y) {
        return (p1x - p3x) * (p2y - p3y) - (p2x - p3x) * (p1y - p3y);
    }
    public boolean isPointInsideTriangle(double x1, double y1, double x2, double y2, double x3, double y3, double px, double py) {
        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(px, py, x1, y1, x2, y2);
        d2 = sign(px, py, x2, y2, x3, y3);
        d3 = sign(px, py, x3, y3, x1, y1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
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
            return collision(p1) || collision(p2) || collision(p3);
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
            if(collision(p1))
                return p1;
            if(collision(p2))
                return p2;
            if(collision(p3))
                return p3;
        }
        return null;
    }

    @Override
    public void setDirection(Point2D point2D) {
        direction = new Direction(point2D);
    }
}
