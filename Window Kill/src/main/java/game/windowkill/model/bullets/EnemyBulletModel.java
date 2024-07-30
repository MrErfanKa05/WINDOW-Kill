package game.windowkill.model.bullets;

import game.windowkill.view.bullet.EnemyBulletView;

import java.util.ArrayList;

public class EnemyBulletModel extends BulletModel{
    public static ArrayList<EnemyBulletModel> enemyBulletModels = new ArrayList<>();

    public EnemyBulletModel(double x, double y, int attackHp) {
        super(x, y, attackHp);
        enemyBulletModels.add(this);
        new EnemyBulletView(id, position);
    }

    public void remove(){
        enemyBulletModels.remove(this);
        EnemyBulletView view = null;
        for(EnemyBulletView enemyBulletView : EnemyBulletView.enemyBulletViews)
            if(enemyBulletView.getId().equals(getId()))
                view = enemyBulletView;
        EnemyBulletView.enemyBulletViews.remove(view);
    }
}
