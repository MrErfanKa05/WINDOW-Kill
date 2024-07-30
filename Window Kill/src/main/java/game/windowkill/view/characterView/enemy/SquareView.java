package game.windowkill.view.characterView.enemy;

import game.windowkill.controller.DataHolder;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.EntityView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class SquareView extends EntityView {
    public static ArrayList<SquareView> squareViews = new ArrayList<>();
    public SquareView(String id, Point2D position, Direction direction){
        this.id = id;
        setPosition(position);
        setDirection(direction);
        squareViews.add(this);
    }
}
