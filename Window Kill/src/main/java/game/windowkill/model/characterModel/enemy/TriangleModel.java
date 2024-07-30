package game.windowkill.model.characterModel.enemy;

import game.windowkill.controller.Utils;
import game.windowkill.model.attack.MeleeAttack;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.collision.normalShape.CollisionTriangleTriangle;
import game.windowkill.model.characterModel.miniBoss.Barricados;
import game.windowkill.model.characterModel.miniBoss.BlackOrb;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.property.EnemyOverlaps;
import game.windowkill.model.characterModel.property.EnemyProperty;
import game.windowkill.model.strategy.Routing;
import game.windowkill.view.characterView.enemy.TriangleView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.SQUARE_OUTSIDE;
import static game.windowkill.controller.Constant.TRIANGLE_OUTSIDE;

public class TriangleModel extends Entity implements CollisionTriangleTriangle, MeleeAttack {
    public static ArrayList<TriangleModel> triangleModels = new ArrayList<>();
    public TriangleModel(double x, double y){
        super(x, y);
        triangleModels.add(this);
        new TriangleView(id, position, direction);
        hp = 15;
        attackHp = 10;
        collectables = 2;
        collectableXP = 5;
        enemyOverlaps = EnemyOverlaps.NonHovering;
        enemyProperty = EnemyProperty.NonRigid;
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

//        Point2D left = Utils.addVector(new Point2D(0, TRIANGLE_OUTSIDE), getPosition());
//        Point2D right = Utils.addVector(new Point2D(TRIANGLE_OUTSIDE, TRIANGLE_OUTSIDE), getPosition());
//        Point2D up = Utils.addVector(new Point2D(TRIANGLE_OUTSIDE/2, 0), getPosition());
        Point2D left = new Point2D(0 + getX(), TRIANGLE_OUTSIDE + getY());
        Point2D right = new Point2D(TRIANGLE_OUTSIDE + getX(), TRIANGLE_OUTSIDE + getY());
        Point2D up = new Point2D(TRIANGLE_OUTSIDE/2 + getX(), 0 + getY());
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
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            return checkTriangleTriangle(this, p1, p2, p3) || checkTriangleTriangle(entity, d1, d2, d3);
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
        if(entity instanceof SquareModel) {
            return entity.getIntersect(this);
        }
        if(entity instanceof TriangleModel){
            Point2D p1 = entity.getVertices().get(0);
            Point2D p2 = entity.getVertices().get(1);
            Point2D p3 = entity.getVertices().get(2);
            Point2D d1 = getVertices().get(0);
            Point2D d2 = getVertices().get(1);
            Point2D d3 = getVertices().get(2);
            Point2D point2D1 = getTriangleTriangle(this, p1, p2, p3);
            Point2D point2D2 = getTriangleTriangle(entity, d1, d2, d3);
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
