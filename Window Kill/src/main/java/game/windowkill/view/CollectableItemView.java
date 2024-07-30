package game.windowkill.view;

import game.windowkill.controller.DataHolder;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.enemy.SquareModel;
import game.windowkill.model.characterModel.enemy.TriangleModel;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CollectableItemView{
    private Point2D position;
    private double radius;
    private Entity type;
    private String id;
    private Color color;
    private int xp;
    public static ArrayList<CollectableItemView> collectableItemViews = new ArrayList<>();

    public CollectableItemView(String id, Point2D position, double radius, Color color, int xp) {
        this.position = position;
        this.radius = radius;
//        this.type = type;
        this.color = color;
        this.id = id;
        this.xp = xp;
        collectableItemViews.add(this);
    }

    public Color getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public Point2D getPosition() {
        double paneX = position.getX();
        double paneY = position.getY();
        return new Point2D(paneX, paneY);
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Entity getType() {
        return type;
    }

    public void setType(Entity type) {
        this.type = type;
    }
}
