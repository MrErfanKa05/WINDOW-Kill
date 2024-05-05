package game.windowkill.model.characterModel.move;

import javafx.geometry.Point2D;

public interface Movable {
//    Point2D;
    void move(Point2D point2D);
    void move(Point2D point2D, double speed);
    default void move(Direction direction, double speed){
        move(direction.getDirectionVector(), speed);
    }
    default void move(Direction direction){
        move(direction.getDirectionVector());
    }
    void setDirection(Point2D point2D);
}
