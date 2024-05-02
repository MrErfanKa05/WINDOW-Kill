package game.windowkill.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Update {
    Timeline updateView = new Timeline(new KeyFrame(Duration.INDEFINITE, actionEvent -> {

    }));
    Timeline updateModel = new Timeline(new KeyFrame(Duration.INDEFINITE, actionEvent -> {

    }));
    public Update(){
        updateModel.setCycleCount(Animation.INDEFINITE);
        updateModel.play();

        updateView.setCycleCount(Animation.INDEFINITE);
        updateView.play();
    }
}
