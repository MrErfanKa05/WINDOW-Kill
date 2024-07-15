package game.windowkill.model.bullets;

import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.Collidable;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.characterModel.move.Movable;
import game.windowkill.view.BulletView;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.UUID;

import static game.windowkill.controller.Constant.BULLET_RADIUS;
import static game.windowkill.controller.Constant.BULLET_SPEED;

public class EpsilonBulletModel extends BulletModel {
    public static ArrayList<EpsilonBulletModel> epsilonBulletModels = new ArrayList<>();

    public EpsilonBulletModel(double x, double y){
        super(x, y);
        epsilonBulletModels.add(this);
        new BulletView(id, position);
    }

    public void remove(){
        epsilonBulletModels.remove(this);
        BulletView nowBulletView = null;
        for(BulletView bulletView : BulletView.bulletViews)
            if(bulletView.getId().equals(getId()))
                nowBulletView = bulletView;
        BulletView.bulletViews.remove(nowBulletView);
    }
}
