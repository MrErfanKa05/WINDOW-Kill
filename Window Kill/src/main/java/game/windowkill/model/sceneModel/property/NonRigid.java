package game.windowkill.model.sceneModel.property;

import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.SceneCollidable;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public interface NonRigid extends SceneCollidable {
    @Override
    default void changeScene(BaseSceneModel baseSceneModel){
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

        if(downAcceleration > 0 && model.isGoDown())
            model.setGoDown(downAcceleration);
        if(leftAcceleration > 0 && model.isGoLeft())
            model.setGoLeft(leftAcceleration);
        if(rightAcceleration > 0 && model.isGoRight())
            model.setGoRight(rightAcceleration);
        if(topAcceleration > 0 && model.isGoTop())
            model.setGoTop(topAcceleration);
//            model.setLeft(model.getLeft() - 30);

//        System.out.println(model.getLeft());
        downAcceleration = max(0, downAcceleration - changeTime);
        leftAcceleration = max(0, leftAcceleration - changeTime);
        rightAcceleration = max(0, rightAcceleration - changeTime);
        topAcceleration = max(0, topAcceleration - changeTime);

        right = model.getRight();
        left = model.getLeft();
        top = model.getTop();
        down = model.getDown();

        top = max(0, top);
        left = max(0, left);
        down = min(SCREEN_HEIGHT, down);
        right = min(SCREEN_WIDTH, right);

//        if(model.isGoRight())
        model.setRight(right);
//        if(model.isGoLeft())
        model.setLeft(left);
//        if(model.isGoTop())
        model.setTop(top);
//        if(model.isGoDown())
        model.setDown(down);

        model.setTopAcceleration(topAcceleration);
        model.setDownAcceleration(downAcceleration);
        model.setRightAcceleration(rightAcceleration);
        model.setLeftAcceleration(leftAcceleration);
    }
}
