package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
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
        //TODO : update here in Update class
        getVertices().clear();
        //TODO : make new vector for vertices;
//        Point2D point2D = Utils.multiplyVector()
//        getVertices().add()
    }

    @Override
    public boolean collision(Point2D point2D) {
        return false;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof EpsilonModel)
            return entity.checkIntersect(this);
        if(entity instanceof SquareModel){

        }
        return false;
    }

    @Override
    public void setDirection(Point2D point2D) {

    }
}
