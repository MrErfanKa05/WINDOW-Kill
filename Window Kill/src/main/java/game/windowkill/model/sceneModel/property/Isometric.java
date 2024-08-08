package game.windowkill.model.sceneModel.property;

import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.model.sceneModel.SceneCollidable;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.SCREEN_HEIGHT;
import static game.windowkill.controller.Constant.SCREEN_WIDTH;
import static java.lang.Math.max;
import static java.lang.Math.min;

public interface Isometric extends SceneCollidable {
    ArrayList<? super Isometric> isometrics = new ArrayList<>();

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

    @Override
    default boolean checkSceneScene(BaseSceneModel baseSceneModel, double left, double right, double top, double down) {
        if(isometrics.contains(baseSceneModel))
            return (collision(new Point2D(baseSceneModel.getLeft(), baseSceneModel.getDown())) ||
                    collision(new Point2D(baseSceneModel.getLeft(), baseSceneModel.getTop())) ||
                    collision(new Point2D(baseSceneModel.getRight(), baseSceneModel.getDown())) ||
                    collision(new Point2D(baseSceneModel.getRight(), baseSceneModel.getTop())) ||
                    baseSceneModel.collision(new Point2D(left, down)) || baseSceneModel.collision(new Point2D(left, top)) ||
                    baseSceneModel.collision(new Point2D(right, down)) || baseSceneModel.collision(new Point2D(right, top)));
        return false;
    }

    default <T extends Isometric> void addIsometric(T t){
        isometrics.add(t);
    }

    default <T extends Isometric> void removeIsometric(T t){
        isometrics.remove(t);
    }

}
