package game.windowkill.controller;

import javafx.scene.paint.Color;

import java.awt.*;

public class Constant {
    public static final int UPS = 100;
    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();;
    public static final double FRAME_UPDATE_TIME = ((double) 1) / FPS;
    public static final double MODEL_UPDATE_TIME = ((double) 1) / UPS;
    public static final double EPSILON_RADIUS = 12.5;
    public static final double EPSILON_DIAMETER = 25;
    public static final double ACCELERATION = 17;
    public static final double ACCELERATION_DECREASE = 10;
    public static final double EPSILON_SPEED = 6;
    public static final double SPEED = 3;
    public static final double BULLET_SPEED = 8;
    public static final double TRIANGLE_DISTRICT = 25;
    public static final double SQUARE_DISTRICT = 25;
    public static final double SQUARE_OUTSIDE = 20;
    public static final double TRIANGLE_OUTSIDE = 20;
    public static final double SQUARE_INSIDE = 12;
    public static final Dimension GLASS_FRAME_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double MAX_WIDTH = 700;
    public static final double MAX_HEIGHT = 700;
    public static final double MIN_HEIGHT = 200;
    public static final double MIN_WIDTH = 200;
    public static final double SCREEN_WIDTH = GLASS_FRAME_DIMENSION.getWidth();
    public static final double SCREEN_HEIGHT = GLASS_FRAME_DIMENSION.getHeight();
    public static final double ACCELERATION_TIME = 1;

    public static final int BULLET_RADIUS = 3;
    public static final int SCENE_CHANGE_ACCELERATION = 1;
    public static final int SCENE_DECREASE_ACCELERATION = 10;
    public static final javafx.scene.paint.Color OUTSIDE_EPSILON_COLOR = Color.WHITE;
    public static final Color INSIDE_EPSILON_COLOR = Color.BLACK;
    public static final int BULLET_DELAY = 250;

    public static final String TRIANGLE_PATH = "src/main/resources/game/windowkill/resources/triangle.png";

}
