package game.windowkill.view.bullet;

import javafx.geometry.Point2D;

public abstract class BulletView {
    protected String id;
    protected Point2D position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BulletView(String id, Point2D position){
        this.position = position;
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
