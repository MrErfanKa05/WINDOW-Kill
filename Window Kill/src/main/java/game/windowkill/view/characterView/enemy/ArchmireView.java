package game.windowkill.view.characterView.enemy;

import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.EntityView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class ArchmireView extends EntityView {
    public static ArrayList<ArchmireView> archmireViews = new ArrayList<>();
    public ArchmireView(String id, Point2D position, Direction direction){
        this.id = id;
        setPosition(position);
        setDirection(direction);
        archmireViews.add(this);
    }
}
