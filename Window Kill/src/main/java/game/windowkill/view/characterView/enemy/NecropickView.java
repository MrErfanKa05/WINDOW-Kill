package game.windowkill.view.characterView.enemy;

import game.windowkill.model.characterModel.enemy.NecroPick;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.EntityView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class NecropickView extends EntityView {
    public static ArrayList<NecropickView> necropickViews = new ArrayList<>();
    public NecropickView(String id, Point2D position, Direction direction){
        this.id = id;
        setPosition(position);
        setDirection(direction);
        necropickViews.add(this);
    }
    public boolean onGround;
}
