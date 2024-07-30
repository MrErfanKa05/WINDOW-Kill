package game.windowkill.view.characterView.enemy;

import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.EntityView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class WyrmView extends EntityView {
    public static ArrayList<WyrmView> wyrmViews = new ArrayList<>();
    public WyrmView(String id, Point2D position, Direction direction){
        this.id = id;
        setPosition(position);
        setDirection(direction);
        wyrmViews.add(this);
    }
}
