package game.windowkill.view.characterView;

import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

public abstract class EntityView {
    protected String id;
    protected Point2D position;
    protected Direction direction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Point2D getPosition() {
        double paneX = position.getX();
        double paneY = position.getY();
        return new Point2D(paneX, paneY);
    }

    public void setPosition(Point2D position) {
//        this.position = new Point2D(position.getX() + DataHolder.stage.getX(), position.getY() + DataHolder.stage.getY());
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
