package game.windowkill.view.bullet;

import game.windowkill.model.bullets.EnemyBulletModel;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class EnemyBulletView extends BulletView{
    public static ArrayList<EnemyBulletView> enemyBulletViews = new ArrayList<>();


    public EnemyBulletView(String id, Point2D point2D){
        super(id, point2D);
        enemyBulletViews.add(this);
    }

    public void remove(){
        enemyBulletViews.remove(this);
    }
}
