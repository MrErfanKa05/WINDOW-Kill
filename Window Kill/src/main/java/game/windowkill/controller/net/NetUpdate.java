package game.windowkill.controller.net;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import static game.windowkill.controller.Constant.FPS;

public class NetUpdate extends Thread{
    Timeline view = new Timeline(new KeyFrame(Duration.millis(FPS * 1000), actionEvent -> {
        String s = Manager.getInstance().getView();
//        for(int i = 0; i < )
    }));
}
