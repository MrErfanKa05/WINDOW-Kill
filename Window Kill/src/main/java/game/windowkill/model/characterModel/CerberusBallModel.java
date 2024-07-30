package game.windowkill.model.characterModel;

import game.windowkill.controller.Constant;
import game.windowkill.controller.Utils;
import game.windowkill.model.attack.AreaOfEffect;
import game.windowkill.model.bullets.BulletModel;
import game.windowkill.model.characterModel.move.Direction;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.CERBERUS_RADIUS;

public class CerberusBallModel extends BulletModel {
    public static ArrayList<CerberusBallModel> cerberusBallModels = new ArrayList<>();
    private double radius;
    private long time = 0;

    public CerberusBallModel(double x, double y, int attackHp){
        super(x, y, attackHp);
        cerberusBallModels.add(this);
        radius = CERBERUS_RADIUS;
        //todo : add in view
    }

    public void remove(){
        cerberusBallModels.remove(this);

        //todo: view
    }

    public long getTime() {
        return time;
    }

    public double getRadius() {
        return radius;
    }

    private boolean checkAreaEffect(Point2D point2D){
        return Utils.getDistance(point2D, getPosition()) <= getRadius();
    }

    public boolean isInsideBall(Entity entity){
        double x = getPosition().getX();
        double y = getPosition().getY();
        return entity.collision(new Point2D(x - CERBERUS_RADIUS, y)) && entity.collision(new Point2D(x + CERBERUS_RADIUS, y)) &&
                entity.collision(new Point2D(x, y - CERBERUS_RADIUS)) && entity.collision(new Point2D(x, y + CERBERUS_RADIUS));
    }

    public void drown(Entity entity){
        long now = System.currentTimeMillis();
        if(isInsideBall(entity))
            if(now - time >= 15_000){
                entity.decreaseHearth(10);
                time = now;
            }

    }
}
