package game.windowkill.view.characterView.miniBoss;

import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.EntityView;
import game.windowkill.view.characterView.enemy.SquareView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class BlackOrbView extends EntityView {
    public static ArrayList<BlackOrbView> blackOrbViews = new ArrayList<>();
    public BlackOrbView(String id, Point2D position, Direction direction){
        this.id = id;
        setPosition(position);
        setDirection(direction);
        blackOrbViews.add(this);
    }
}
