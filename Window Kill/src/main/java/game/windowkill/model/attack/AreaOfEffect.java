package game.windowkill.model.attack;

import game.windowkill.controller.Constant;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public interface AreaOfEffect {
    ArrayList<Point2D> areas = new ArrayList<>();

    default void addArea(Point2D point2D){
        areas.add(point2D);
    }
    default boolean checkAreaEffect(Point2D point2D){
        if(areas.size() == 0)
            return false;
        {
            Point2D point = areas.getLast();
            double x = point.getX() - point2D.getX();
            double y = point.getY() - point2D.getY();
            if ((x * x) + (y * y) <= Constant.ARCHMIRE_OUTSIDE)
                if (x < 0 && y < 0)
                    return true;
        }
        for(Point2D point : areas){
            double x = point.getX() - point2D.getX();
            double y = point.getY() - point2D.getY();
            if ((x * x) + (y * y) <= Constant.ARCHMIRE_OUTSIDE)
                if (x < 0 && y < 0)
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
            if ((x * x) + (y * y) <= Constant.ARCHMIRE_OUTSIDE)
                if (x < 0 && y < 0)
                    return Constant.DROWN;
        }
        for(Point2D point : areas){
            double x = point.getX() - point2D.getX();
            double y = point.getY() - point2D.getY();
            if ((x * x) + (y * y) <= Constant.ARCHMIRE_OUTSIDE)
                if (x < 0 && y < 0)
                    return Constant.AOE;
        }
        return 0;

    }
}
