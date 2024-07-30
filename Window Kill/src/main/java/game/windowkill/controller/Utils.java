package game.windowkill.controller;

import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.EPSILON_SPEED;
import static game.windowkill.controller.Constant.SPEED;

public class Utils {
    public static double getEpsilonSpeed(){
        return EPSILON_SPEED/10 + 9*EPSILON_SPEED/10 * DataHolder.sensitivity / 100;
    }
    public static double getSpeed(){
        return SPEED/10 + 9*SPEED/10 * DataHolder.sensitivity / 100;
    }
    public static Point2D addVector(Point2D point2D1, Point2D point2D2){
        return new Point2D(point2D2.getX() + point2D1.getX(), point2D2.getY() + point2D1.getY());
    }
//    public static Point2D unitVector(Point2D point2D){
//        double size = getDistance(point2D, new Point2D(0, 0));
//        return new Point2D(1, point2D.getY()/point2D.getX());
//    }
    public static Point2D multiplyVector(Point2D point2D, double scalar){
        return new Point2D(point2D.getX() * scalar, point2D.getY() * scalar);
    }
    public static Point2D relativeLocation(Point2D point2D1,Point2D point2D2){
        return new Point2D(point2D1.getX() - point2D2.getX(),point2D1.getY() - point2D2.getY());
    }
    public static Point2D weightedAddVectors(Point2D point1,Point2D point2,double weight1,double weight2){
        return multiplyVector(addVector(multiplyVector(point1,weight1),multiplyVector(point2,weight2)),1/(weight1+weight2));
    }
    public static double getDistance(Point2D point1, Point2D point2){
        return Math.sqrt((point2.getX() - point1.getX()) * (point2.getX() - point1.getX()) +
                (point2.getY() - point1.getY()) * (point2.getY() - point1.getY()));
    }
}
