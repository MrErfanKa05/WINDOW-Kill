package game.windowkill.view.bullet;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class EpsilonBulletView extends BulletView{
    public static ArrayList<EpsilonBulletView> epsilonBulletViews = new ArrayList<>();

    public EpsilonBulletView(String id, Point2D position) {
        super(id, position);
        epsilonBulletViews.add(this);
    }

    public void remove(){
        epsilonBulletViews.remove(this);
    }


}
