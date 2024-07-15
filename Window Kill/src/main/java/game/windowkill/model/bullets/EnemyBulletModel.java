package game.windowkill.model.bullets;

import game.windowkill.view.BulletView;

import java.util.ArrayList;

public class EnemyBulletModel extends BulletModel{
    public static ArrayList<EnemyBulletModel> enemyBulletModels = new ArrayList<>();

    public EnemyBulletModel(double x, double y) {
        super(x, y);
        enemyBulletModels.add(this);
//        new BulletView(id, position);
    }

    public void remove(){
        enemyBulletModels.remove(this);
//        BulletView nowBulletView = null;
//        for(BulletView bulletView : BulletView.bulletViews)
//            if(bulletView.getId().equals(getId()))
//                nowBulletView = bulletView;
//        BulletView.bulletViews.remove(nowBulletView);
    }
}
