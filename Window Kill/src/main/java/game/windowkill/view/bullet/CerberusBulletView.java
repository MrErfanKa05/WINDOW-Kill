package game.windowkill.view.bullet;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class CerberusBulletView extends BulletView{
    public static ArrayList<CerberusBulletView> cerberusBulletViews = new ArrayList<>();


    public CerberusBulletView(String id, Point2D point2D){
        super(id, point2D);
        cerberusBulletViews.add(this);
    }

    public void remove(){
        cerberusBulletViews.remove(this);
    }
}
