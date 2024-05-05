package game.windowkill.controller;

import game.windowkill.controller.builder.EnemyBuilder;
import game.windowkill.model.BulletModel;
import game.windowkill.model.MotionSceneModel;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.SquareModel;
import game.windowkill.model.characterModel.TriangleModel;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.BulletView;
import game.windowkill.view.MotionSceneView;
import game.windowkill.view.characterView.EpsilonView;
import game.windowkill.view.characterView.SquareView;
import game.windowkill.view.characterView.TriangleView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static game.windowkill.controller.Constant.*;
import static game.windowkill.controller.DataHolder.stage;

public class Update {
    private InputManager inputManager;
    private EnemyBuilder enemyBuilder;
    private double dalay = 0;
    Timeline updateView = new Timeline(new KeyFrame(Duration.millis(FRAME_UPDATE_TIME * 1000), actionEvent -> {
        MotionSceneModel model = MotionSceneModel.getInstance();
        MotionSceneView.getInstance().set(model.getTop(), model.getDown(), model.getRight(), model.getLeft());

        EpsilonView.getInstance().setPosition(EpsilonModel.getInstance().getPosition());

        for(BulletView bulletView : BulletView.bulletViews){
            for(BulletModel bulletModel : BulletModel.bulletModels) {
//                System.out.println(bulletModel.getId() + " / " + bulletView.getId());
                if (bulletModel.getId().equals(bulletView.getId())) {
                    bulletView.setPosition(bulletModel.getPosition());
//                    System.out.println("do");
                }
            }
        }

        for(SquareView squareView : SquareView.squareViews){
            for (SquareModel squareModel : SquareModel.squareModels){
                if(squareModel.getId().equals(squareView.getId())){
                    squareView.setPosition(squareModel.getPosition());
                }
            }
        }

        for(TriangleView triangleView : TriangleView.triangleViews){
            for (TriangleModel triangleModel : TriangleModel.triangleModels){
                if(triangleModel.getId().equals(triangleView.getId())){
                    triangleView.setPosition(triangleModel.getPosition());
                }
            }
        }
//        System.out.println(MotionSceneView.getInstance());
    }));
    Timeline updateModel = new Timeline(new KeyFrame(Duration.millis(MODEL_UPDATE_TIME * 1000), actionEvent -> {
//        System.out.println("ff");
//        switch (enemyBuilder.wave){
//            case 4 : finish();
//            case 3 : enemyBuilder.playWave3(); break;
//            case 2 : enemyBuilder.playWave2(); break;
//            case 1 : enemyBuilder.playWave1(); break;
//        }
        if(inputManager.isUp())
            EpsilonModel.getInstance().move(new Direction(270));
        if(inputManager.isDown())
            EpsilonModel.getInstance().move(new Direction(90));
        if(inputManager.isLeft())
            EpsilonModel.getInstance().move(new Direction(180));
        if(inputManager.isRight())
            EpsilonModel.getInstance().move(new Direction(0));
        if(Math.random()*100 < 0.5)
            MotionSceneModel.getInstance().change(1, 0);
        if(Math.random()*100 < 1)
            new SquareModel(100, 70);
        if(Math.random()*100 < 1)
            new TriangleModel(20, 70);
        MotionSceneView.getInstance().paint();

    }));
    Timeline finishTimeLine = new Timeline(new KeyFrame(Duration.millis(UPS), actionEvent -> {

    }));
    private void death(){
        updateModel.stop();
        updateView.stop();
        //TODO : complete this
    }
    private void exit(){
        updateModel.stop();
        updateView.stop();
        finishTimeLine.stop();

    }
    public void finish(){
//        updateModel.stop();
//        finishTimeLine.setCycleCount(Animation.INDEFINITE);
//        finishTimeLine.play();
    }
    public Update(){
        stage.setHeight(700);
        stage.setWidth(700);

        updateModel.setCycleCount(Animation.INDEFINITE);
        updateView.setCycleCount(Animation.INDEFINITE);

        MotionSceneView.getInstance().init();
        MotionSceneView.getInstance().inputManager.setOnLeftClick(newMouseEvent -> shoot(newMouseEvent.getMousePosition()));
        inputManager = MotionSceneView.getInstance().getInputManager();
        enemyBuilder = new EnemyBuilder();

        updateModel.play();
        updateView.play();
    }
    public void shoot(Point2D point2D){
        if(System.currentTimeMillis() - dalay > 500) {
            new BulletModel(point2D.getX(), point2D.getY());
//            System.out.println(point2D.getX() + " " + point2D.getY());
            dalay = System.currentTimeMillis();
        }
    }
}
