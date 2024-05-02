package game.windowkill.model.characterModel;

import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;
import static game.windowkill.controller.Constant.MAX_WIDTH;
import static game.windowkill.controller.Constant.MAX_HEIGHT;

public class EpsilonModel extends Entity{
    public static EpsilonModel epsilonModel;
    public static EpsilonModel getInstance(){
        if(epsilonModel == null)epsilonModel = new EpsilonModel();
        return epsilonModel;
    }

    private double radius;
    private EpsilonModel() {
        super(MAX_WIDTH /2 , MAX_HEIGHT /2);
        radius = EPSILON_RADIUS;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean collision(Point2D point2D) {
        if(getDistance(position, point2D) <= EPSILON_RADIUS)
            return true;
        return false;
    }
    public boolean pointInsideCircle(double x1, double y1, double x2, double y2){
        double lineLengthX = x2 - x1;
        double lineLengthY = y2 - y1;

        double t = ((getX() - x1) * lineLengthX + (getY() - y1) * lineLengthY) /
                (lineLengthX * lineLengthX + lineLengthY * lineLengthY);
        t = Math.max(0, Math.min(t, 1));

        double nearestX = x1 + t * lineLengthX;
        double nearestY = y1 + t * lineLengthY;

        return getDistance(new Point2D(nearestX, nearestY), new Point2D(getX(), getY())) <= radius;
    }

    @Override
    public boolean checkIntersect(Entity entity) {
        if(entity instanceof TriangleModel){

        }
        else if(entity instanceof SquareModel){

        }
        return false;
    }

    @Override
    public void update() {
        return;
    }

    @Override
    public void move(Point2D point2D) {

    }

    @Override
    public void move(Point2D point2D, double speed) {

    }

    @Override
    public void setMoves(Point2D point2D) {

    }
}
