package game.windowkill.controller;

import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.SPEED;

public class Utils {
    public static double getSpeed(){
        return SPEED/3 + 2*SPEED/3 * DataHolder.difficulty / 100;
    }
    public static Point2D addVector(Point2D point2D1, Point2D point2D2){
        return new Point2D(point2D2.getX() + point2D1.getX(), point2D2.getY() + point2D1.getY());
    }
    public static Point2D multiplyVector(Point2D point2D, double scalar){
        return new Point2D(point2D.getX() * scalar, point2D.getY() * scalar);
    }
    public static Point2D relativeLocation(Point2D point2D1,Point2D point2D2){
        return new Point2D(point2D1.getX() - point2D2.getX(),point2D1.getY() - point2D2.getY());
    }
    public static Point2D weightedAddVectors(Point2D point1,Point2D point2,double weight1,double weight2){
        return multiplyVector(addVector(multiplyVector(point1,weight1),multiplyVector(point2,weight2)),1/(weight1+weight2));
    }
}
