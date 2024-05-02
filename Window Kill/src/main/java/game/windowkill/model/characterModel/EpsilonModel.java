package game.windowkill.model.characterModel;

import game.windowkill.controller.Constant;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.EPSILON_RADIUS;

public class EpsilonModel extends Entity{
    public static EpsilonModel epsilonModel;
    public static EpsilonModel getInstance(){
        if(epsilonModel == null)epsilonModel = new EpsilonModel();
        return epsilonModel;
    }
    private int x, y;

    @Override
    public boolean collision(Point2D point2D) {
        if(getDistance(point2D) <= EPSILON_RADIUS)
            return true;
        return false;
    }

    @Override
    public boolean checkCollision(Entity entity) {

    }
}
