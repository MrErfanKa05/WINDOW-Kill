package game.windowkill.view;

import game.windowkill.controller.DataHolder;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class BulletView {
    public static ArrayList<BulletView> bulletViews = new ArrayList<>();
    private String id;
    private Point2D position;

    public BulletView(String id, Point2D position) {
        this.id = id;
        this.position = position;
        bulletViews.add(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Point2D getPosition() {
        double paneX = position.getX();
        double paneY = position.getY();
//        System.out.println(position.getX() + " " + paneX);
        return new Point2D(paneX, paneY);
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
