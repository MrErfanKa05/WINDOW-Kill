package game.windowkill.view.characterView;

import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class SquareView {
    public static ArrayList<SquareView> squareViews = new ArrayList<>();
    private String id;
    private Point2D position;
    private Direction direction;
    public SquareView(String id, Point2D position, Direction direction){
        this.id = id;
        this.position = position;
        this.direction = direction;
        squareViews.add(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
