package game.windowkill.controller;

import static game.windowkill.controller.Constant.SPEED;

public class Utils {
    public static double getSpeed(){
        return SPEED/3 + 2*SPEED/3 * DataHolder.difficulty / 100;
    }
}
