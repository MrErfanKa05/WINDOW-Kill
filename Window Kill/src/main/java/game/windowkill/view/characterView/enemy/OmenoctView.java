package game.windowkill.view.characterView.enemy;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.EntityView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class OmenoctView extends EntityView {
    public static ArrayList<OmenoctView> omenoctViews = new ArrayList<>();
    public OmenoctView(String id, Point2D position, Direction direction){
        this.id = id;
        setPosition(position);
        setDirection(direction);
        omenoctViews.add(this);
    }
}
