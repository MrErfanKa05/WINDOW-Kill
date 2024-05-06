package game.windowkill.model.characterModel;

import game.windowkill.controller.Utils;
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
        //TODO

    }

    @Override
    public boolean collision(Point2D point2D) {
        return false;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        return false;
    }

    @Override
    public void setDirection(Point2D point2D) {

    }
}
