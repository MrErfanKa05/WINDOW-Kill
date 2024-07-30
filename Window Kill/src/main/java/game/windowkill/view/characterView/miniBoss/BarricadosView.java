package game.windowkill.view.characterView.miniBoss;

import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.characterView.EntityView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class BarricadosView extends EntityView {
    public static ArrayList<BarricadosView> barricadosViews = new ArrayList<>();
    public BarricadosView(String id, Point2D position, Direction direction){
        this.id = id;
        setPosition(position);
        setDirection(direction);
        barricadosViews.add(this);
    }
}
