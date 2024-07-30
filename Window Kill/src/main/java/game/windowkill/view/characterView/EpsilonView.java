package game.windowkill.view.characterView;

import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

public class EpsilonView {
    private static EpsilonView epsilonView;

    public static EpsilonView getInstance() {
        if(epsilonView == null) epsilonView = new EpsilonView();
        return epsilonView;
    }

    private EpsilonView(){
        setDirection(EpsilonModel.getInstance().getDirection());
        setPosition(EpsilonModel.getInstance().getPosition());
        setRadius(EpsilonModel.getInstance().getRadius());
    }

    private Point2D position;
    private Direction direction;
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setPosition(Point2D position){
//        this.position = new Point2D(position.getX() + MotionSceneView.getInstance().getLeft(),
//                position.getY() + MotionSceneView.getInstance().getTop());
        this.position = position;
    }
    private void setDirection(Direction direction){
        this.direction = direction;
    }

    public Point2D getPosition() {
        double paneX = position.getX();
        double paneY = position.getY();
        return new Point2D(paneX, paneY);
    }

    public Direction getDirection() {
        return direction;
    }

    public double getX() {
        return getPosition().getX();
    }
    public double getY(){
        return getPosition().getY();
    }
}
