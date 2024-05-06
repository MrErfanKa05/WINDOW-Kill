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

import java.util.ArrayList;

import static game.windowkill.controller.Constant.*;
import static game.windowkill.controller.DataHolder.stage;

public class Update {
    private InputManager inputManager;
    private EnemyBuilder enemyBuilder;
    private double dalay = 0;
    Timeline updateView = new Timeline(new KeyFrame(Duration.millis(FRAME_UPDATE_TIME * 1000), actionEvent -> {
        EpsilonView.getInstance().setPosition(EpsilonModel.getInstance().getPosition());

        for(BulletView bulletView : BulletView.bulletViews){
            for(BulletModel bulletModel : BulletModel.bulletModels) {
                System.out.println(bulletModel.getId() + " / " + bulletView.getPosition());
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

        MotionSceneModel model = MotionSceneModel.getInstance();
        MotionSceneView.getInstance().set(model.getTop(), model.getDown(), model.getRight(), model.getLeft());
        MotionSceneView.getInstance().paint();

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
//        EpsilonView.getInstance().setPosition(EpsilonModel.getInstance().getPosition());
        update();

        ArrayList<BulletModel> Bullets = new ArrayList<>();

        for(BulletModel bulletModel : BulletModel.bulletModels)
            if(MotionSceneModel.getInstance().collision(bulletModel.getPosition())){
                Point2D point2D = bulletModel.getPosition();
                if(point2D.getY() - MotionSceneModel.getInstance().getTop() <= 1)
                    MotionSceneModel.getInstance().change(0, -1);
                else if(MotionSceneModel.getInstance().getDown() - point2D.getY() <= 1)
                    MotionSceneModel.getInstance().change(0, 1);
                else if(point2D.getX() - MotionSceneModel.getInstance().getLeft() <= 1)
                    MotionSceneModel.getInstance().change(-1, 0);
                else if(MotionSceneModel.getInstance().getRight() - point2D.getX() <= 1)
                    MotionSceneModel.getInstance().change(1, 0);
                Bullets.add(bulletModel);
            }

        //TODO : like bullet for others;

        for(BulletModel bulletModel : Bullets)
            bulletModel.remove();

        for(SquareModel squareModel : SquareModel.squareModels)
            squareModel.move(new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(), squareModel.getPosition())));
        if(inputManager.isUp())
            EpsilonModel.getInstance().move(new Direction(270));
        if(inputManager.isDown())
            EpsilonModel.getInstance().move(new Direction(90));
        if(inputManager.isLeft())
            EpsilonModel.getInstance().move(new Direction(180));
        if(inputManager.isRight())
            EpsilonModel.getInstance().move(new Direction(0));
//        if(Math.random()*100 < 0.5)
//            MotionSceneModel.getInstance().change(1, 0);
//        if(Math.random()*100 < 1)
//            new SquareModel(stage.getX() + 100,stage.getY() + 70);
//        if(Math.random()*100 < 1)
//            new TriangleModel(stage.getX() + 20, stage.getY() + 70);
    }));
    Timeline finishTimeLine = new Timeline(new KeyFrame(Duration.millis(UPS), actionEvent -> {

    }));
    private void update(){
        EpsilonModel.getInstance().update();
        for(SquareModel squareModel : SquareModel.squareModels)
            squareModel.update();
        for(TriangleModel triangleModel : TriangleModel.triangleModels)
            triangleModel.update();
        for(BulletModel bulletModel : BulletModel.bulletModels)
            bulletModel.update();
    }
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
        if(System.currentTimeMillis() - dalay > BULLET_DELAY) {
//            new BulletModel(point2D.getX() + stage.getX(), point2D.getY() + stage.getY());
            Point2D pos = new Point2D(EpsilonModel.getInstance().getX(), EpsilonModel.getInstance().getY());
            BulletModel bulletModel = new BulletModel(pos.getX(), pos.getY());
            bulletModel.setDirection(new Direction(Utils.relativeLocation(new Point2D(point2D.getX() + stage.getX(), point2D.getY() + stage.getY()),
                    pos)).getDirectionVector());
//            System.out.println(point2D.getX() + " " + point2D.getY());
            dalay = System.currentTimeMillis();
        }
    }
}
