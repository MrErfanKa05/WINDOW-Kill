package game.windowkill.model.characterModel.collision;

import javafx.geometry.Point2D;

public interface Movable {
//    Point2D;
    void move(Point2D point2D);
    void move(Point2D point2D, double speed);
    void setMoves(Point2D point2D);
}
