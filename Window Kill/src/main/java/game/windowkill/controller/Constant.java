package game.windowkill.controller;

import java.awt.*;

public class Constant {
    public static final int UPS = 100;
    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();;
    public static final double FRAME_UPDATE_TIME = (double) 1L / FPS;
    public static final double MODEL_UPDATE_TIME = (double) 1L / UPS;
    public static final double EPSILON_RADIUS = 12.5;
    public static final double ACCELERATION = 10;
    public static final double ACCELERATION_DECREASE = 10;
    public static final double SPEED = 20;
    public static final double BULLET_SPEED = 50;
}
