package game.windowkill.model.sceneModel.property;

import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.SceneCollidable;

import static game.windowkill.controller.Constant.SCREEN_HEIGHT;
import static game.windowkill.controller.Constant.SCREEN_WIDTH;
import static java.lang.Math.max;
import static java.lang.Math.min;

public interface NonIsometric extends SceneCollidable {
    @Override
    default void shrinkage(BaseSceneModel model) {
        double right = model.getRight();
        double left = model.getLeft();
        double down = model.getDown();
        double top = model.getTop();
        double changeFrame = BaseSceneModel.getChangeFrame();
        double rightAcceleration = model.getRightAcceleration();
        double leftAcceleration = model.getLeftAcceleration();
        double topAcceleration = model.getTopAcceleration();
        double downAcceleration = model.getDownAcceleration();

        //todo here for mistake
//        System.out.println("Dsdasdsadsada");

        if(right - left <= model.width){
            if(model.isGoRight())
                right += changeFrame;
            if(model.isGoLeft())
                left -= changeFrame;
        }
        if(down - top <= model.height){
            if(model.isGoDown())
                down += changeFrame;
            if(model.isGoTop())
                top -= changeFrame;
        }
        if(model.isGoDown())
            down -= changeFrame;
        if(model.isGoTop())
            top += changeFrame;
        if(model.isGoRight())
            right -= changeFrame;
        if(model.isGoLeft())
            left += changeFrame;

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
