package game.windowkill.model.sceneModel.property;

import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.SceneCollidable;

import static game.windowkill.controller.Constant.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public interface NonIsometric extends SceneCollidable {
    @Override
    default void changeScene(BaseSceneModel baseSceneModel){
        shrinkage(baseSceneModel);
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

        if(downAcceleration > 0)
            model.setGoDown(downAcceleration);
        if(leftAcceleration > 0)
            model.setGoLeft(leftAcceleration);
        if(rightAcceleration > 0)
            model.setGoRight(rightAcceleration);
        if(topAcceleration > 0)
            model.setGoTop(topAcceleration);

        downAcceleration = max(0, downAcceleration - changeTime);
        leftAcceleration = max(0, leftAcceleration - changeTime);
        rightAcceleration = max(0, rightAcceleration - changeTime);
        topAcceleration = max(0, topAcceleration - changeTime);

        top = max(0, top);
        left = max(0, left);
        down = min(SCREEN_HEIGHT, down);
        right = min(SCREEN_WIDTH, right);

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

    default void shrinkage(BaseSceneModel model){
        double right = model.getRight();
        double left = model.getLeft();
        double down = model.getDown();
        double top = model.getTop();
        double changeFrame = model.getChangeFrame();
        double rightAcceleration = model.getRightAcceleration();
        double leftAcceleration = model.getLeftAcceleration();
        double topAcceleration = model.getTopAcceleration();
        double downAcceleration = model.getDownAcceleration();

        //todo here for mistake

        if(right - left <= model.width){
            right += changeFrame;
            left -= changeFrame;
        }
        if(down - top <= model.height){
            down += changeFrame;
            top -= changeFrame;
        }
        down -= changeFrame;
        top += changeFrame;
        right -= changeFrame;
        left += changeFrame;

        top = max(0, top);
        left = max(0, left);
        down = min(SCREEN_HEIGHT, down);
        right = min(SCREEN_WIDTH, right);

        if(model.isGoRight())
            model.setRight(right);
        if(model.isGoLeft())
            model.setLeft(left);
        if(model.isGoTop())
            model.setTop(top);
        if(model.isGoDown())
            model.setDown(down);

        if(right + rightAcceleration - left + leftAcceleration<= 202){
//            right++;
//            left--;
            rightAcceleration = 0;
            leftAcceleration = 0;
        }
        if(down + downAcceleration - top + topAcceleration <= 202){
//            top--;
//            down++;
            topAcceleration = 0;
            downAcceleration = 0;
        }

        model.setRightAcceleration(rightAcceleration);
        model.setLeftAcceleration(leftAcceleration);
        model.setDownAcceleration(downAcceleration);
        model.setTopAcceleration(topAcceleration);
    }
}
