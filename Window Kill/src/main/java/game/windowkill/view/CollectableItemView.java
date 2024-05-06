package game.windowkill.view;

import game.windowkill.controller.DataHolder;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.SquareModel;
import game.windowkill.model.characterModel.TriangleModel;
import game.windowkill.model.characterModel.collision.Collidable;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CollectableItemView{
    private Point2D position;
    private double radius;
    private Entity type;
    private String id;
    private Color color;
    public static ArrayList<CollectableItemView> collectableItemViews = new ArrayList<>();

    public CollectableItemView(String id, Point2D position, double radius, Entity type) {
        this.position = position;
        this.radius = radius;
//        this.type = type;
        if(type instanceof TriangleModel)
            color = Color.YELLOW;
        if(type instanceof SquareModel)
            color = Color.GREEN;
        this.id = id;
        collectableItemViews.add(this);
    }

    public Color getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public Point2D getPosition() {
        double paneX = position.getX() - DataHolder.stage.getX();
        double paneY = position.getY() - DataHolder.stage.getY();
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
