package game.windowkill.model.sceneModel.property;

import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.SceneCollidable;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.SCREEN_HEIGHT;
import static game.windowkill.controller.Constant.SCREEN_WIDTH;
import static java.lang.Math.max;
import static java.lang.Math.min;

public interface Isometric extends SceneCollidable {
    ArrayList<? super Isometric>  isometrics = new ArrayList<>();

    default <T extends Isometric> void add(T t){
        isometrics.add(t);
    }

    @Override
    default void changeScene(BaseSceneModel baseSceneModel) {
        move(baseSceneModel);
    }

    default void move(BaseSceneModel model){
        double right = model.getRight();
        double left = model.getLeft();
        double down = model.getDown();
        double top = model.getTop();
        double changeTime = model.getChangeTime();
        double rightAcceleration = model.getRightAcceleration();
        double leftAcceleration = model.getLeftAcceleration();
        double topAcceleration = model.getTopAcceleration();
        double downAcceleration = model.getDownAcceleration();

        if(downAcceleration > 0) {
//            model.setGoDown(downAcceleration);
            model.setGoTop(-downAcceleration);
        }
        if(leftAcceleration > 0) {
            model.setGoLeft(leftAcceleration);
//            model.setGoRight(-leftAcceleration);
        }
        if(rightAcceleration > 0) {
//            model.setGoRight(rightAcceleration);
            model.setGoLeft(-rightAcceleration);
        }
        if(topAcceleration > 0) {
            model.setGoTop(topAcceleration);
//            model.setGoDown(-topAcceleration);
        }

        downAcceleration = max(0, downAcceleration - changeTime);
        leftAcceleration = max(0, leftAcceleration - changeTime);
        rightAcceleration = max(0, rightAcceleration - changeTime);
        topAcceleration = max(0, topAcceleration - changeTime);

        top = max(0, top);
        left = max(0, left);
        down = top + model.height;
        right = left + model.width;
        down = min(SCREEN_HEIGHT, down);
        right = min(SCREEN_WIDTH, right);
        left = right - model.width;
        top = down - model.height;

        if(model.isGoRight())
            model.setRight(right);
        if(model.isGoLeft())
            model.setLeft(left);
        if(model.isGoTop())
            model.setTop(top);
        if(model.isGoDown())
            model.setDown(down);

        model.setTopAcceleration(topAcceleration);
        model.setDownAcceleration(downAcceleration);
        model.setRightAcceleration(rightAcceleration);
        model.setLeftAcceleration(leftAcceleration);
    }
}
