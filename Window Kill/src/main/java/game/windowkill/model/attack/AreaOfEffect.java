package game.windowkill.model.attack;

import game.windowkill.controller.Constant;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static game.windowkill.controller.Constant.*;

public interface AreaOfEffect {
    ArrayList<Point2D> areas = new ArrayList<>();
    Map<Point2D, Long> times = new HashMap<>();
    default void addArea(Point2D point2D){
        areas.add(point2D);
        times.put(point2D, System.currentTimeMillis());
    }
    static void deleteArea(){
        long now = System.currentTimeMillis();
        ArrayList<Point2D> delete = new ArrayList<>();
        for (Point2D area : areas) {
            if (now - times.get(area) > 5000) {
                delete.add(area);
            }
        }
        for(Point2D point2D : delete){
            times.remove(point2D);
            areas.remove(point2D);
        }
    }
    default boolean checkAreaEffect(Point2D point2D){
        if(areas.isEmpty())
            return false;
        {
            Point2D point = areas.getLast();
            double x = point.getX() - point2D.getX();
            double y = point.getY() - point2D.getY();
            if (x <= BIG_ENEMY_OUTSIDE && y <= BIG_ENEMY_OUTSIDE)
                    return true;
        }
        for(Point2D point : areas){
            double x = point.getX() - point2D.getX();
            double y = point.getY() - point2D.getY();
            if (x <= BIG_ENEMY_OUTSIDE && y <= BIG_ENEMY_OUTSIDE)
                return true;
        }
        return false;
    }
    default int getAreaEffect(Point2D point2D){
        if(areas.size() == 0)
            return 0;
        {
            Point2D point = areas.getLast();
            double x = point.getX() - point2D.getX();
            double y = point.getY() - point2D.getY();
            if (x <= BIG_ENEMY_OUTSIDE && y <= BIG_ENEMY_OUTSIDE)
                return DROWN;
        }
        for(Point2D point : areas){
            double x = point.getX() - point2D.getX();
            double y = point.getY() - point2D.getY();
            if (x <= BIG_ENEMY_OUTSIDE && y <= BIG_ENEMY_OUTSIDE)
                return AOE;
        }
        return 0;

    }
}
