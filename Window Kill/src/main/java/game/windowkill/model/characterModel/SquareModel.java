package game.windowkill.model.characterModel;

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
    }
    @Override
    public void update() {

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
    public void move(Point2D point2D) {

    }

    @Override
    public void move(Point2D point2D, double speed) {

    }

    @Override
    public void setMoves(Point2D point2D) {

    }
}
