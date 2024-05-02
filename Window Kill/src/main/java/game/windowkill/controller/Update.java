package game.windowkill.controller;

import game.windowkill.view.MotionSceneView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static game.windowkill.controller.DataHolder.stage;

public class Update {
    InputManager inputManager;
    Timeline updateView = new Timeline(new KeyFrame(Duration.INDEFINITE, actionEvent -> {

    }));
    Timeline updateModel = new Timeline(new KeyFrame(Duration.INDEFINITE, actionEvent -> {

    }));
    public Update(){
        updateModel.setCycleCount(Animation.INDEFINITE);
        updateModel.play();

        updateView.setCycleCount(Animation.INDEFINITE);
        updateView.play();

        MotionSceneView.getInstance().inputManager.setOnLeftClick(newMouseEvent -> shoot(newMouseEvent.getMousePosition()));
    }
    public void shoot(Point2D point2D){

    }
}
