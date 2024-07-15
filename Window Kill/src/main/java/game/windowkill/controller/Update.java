package game.windowkill.controller;

import game.windowkill.controller.builder.EnemyBuilder;
import game.windowkill.controller.loader.SceneLoader;
import game.windowkill.model.bullets.EpsilonBulletModel;
import game.windowkill.model.CollectableItemModel;
import game.windowkill.model.attack.MeleeAttack;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.sceneModel.MotionSceneModel;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.enemy.SquareModel;
import game.windowkill.model.characterModel.enemy.TriangleModel;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.model.strategy.Routing;
import game.windowkill.view.BulletView;
import game.windowkill.view.CollectableItemView;
import game.windowkill.view.MotionSceneView;
import game.windowkill.view.characterView.EpsilonView;
import game.windowkill.view.characterView.enemy.SquareView;
import game.windowkill.view.characterView.enemy.TriangleView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.ArrayList;

import static game.windowkill.controller.Constant.*;
import static game.windowkill.controller.DataHolder.stage;
import static java.lang.Math.max;

public class Update {
    private InputManager inputManager;
    private EnemyBuilder enemyBuilder;
    private double dalay = 0;
    Timeline updateView = new Timeline(new KeyFrame(Duration.millis(FRAME_UPDATE_TIME * 1000), actionEvent -> {
        MotionSceneModel model = MotionSceneModel.getInstance();
        EpsilonView.getInstance().setPosition(EpsilonModel.getInstance().getPosition());

        for(BulletView bulletView : BulletView.bulletViews){
            for(EpsilonBulletModel epsilonBulletModel : EpsilonBulletModel.epsilonBulletModels) {
                if (epsilonBulletModel.getId().equals(bulletView.getId())) {
                    bulletView.setPosition(epsilonBulletModel.getPosition());
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

        for(CollectableItemView collectableItemView : CollectableItemView.collectableItemViews){
            for(CollectableItemModel collectableItemModel : CollectableItemModel.collectableItemModels){
                if(collectableItemModel.getId().equals(collectableItemView.getId()))
                    collectableItemView.setPosition(collectableItemModel.getPosition());
            }
        }

        model = MotionSceneModel.getInstance();
        MotionSceneView.getInstance().set(model.getTop(), model.getDown(), model.getRight(), model.getLeft());
        MotionSceneView.getInstance().paint();

    }));
    Timeline updateModel = new Timeline(new KeyFrame(Duration.millis(MODEL_UPDATE_TIME * 1000), actionEvent -> {
        playWave();

        update();

        bulletIntersect();

        entityIntersect();

        entityDeath();

        collectableGet();

        move();

//        System.out.println(EpsilonModel.getInstance().getPosition());

    }));

    public void playWave(){
        if(enemyBuilder.waveFinish){
            switch (enemyBuilder.wave) {
                case 4:
                    win();
                case 3:
                    enemyBuilder.playWave3();
                    break;
                case 2:
                    enemyBuilder.playWave2();
                    break;
                case 1:
                    enemyBuilder.playWave1();
                    break;
            }
        }
    }

    public void entityDeath(){
        ArrayList<TriangleModel> triangleModels = new ArrayList<>();
        for(TriangleModel triangleModel : TriangleModel.triangleModels)
            if(triangleModel.getHp() <= 0) {
                triangleModels.add(triangleModel);
                new CollectableItemModel(triangleModel.getPosition(), 5, triangleModel);
                new CollectableItemModel(Utils.addVector(triangleModel.getPosition(), new Point2D(5, 2)), 5, triangleModel);
            }
        for(TriangleModel triangleModel : triangleModels)
            triangleModel.remove();

        ArrayList<SquareModel> squareModels = new ArrayList<>();
        for(SquareModel squareModel : SquareModel.squareModels)
            if(squareModel.getHp() <= 0) {
                squareModels.add(squareModel);
                new CollectableItemModel(squareModel.getPosition(), 5, squareModel);
            }
        for(SquareModel squareModel : squareModels)
            squareModel.remove();

        if(EpsilonModel.getInstance().getHp() <= 0)death();
    }

    public void entityIntersect(){
        for(Entity entity : Entity.entities) {
            for (Entity entity1 : Entity.entities) {
                if (entity.getId().equals(entity1.getId()))
                    continue;
                if (entity.routing.equals(Routing.Isometric) && entity1.routing.equals(Routing.Isometric) && entity.checkIntersect(entity1)) {
                    impact(entity.getIntersect(entity1));
                    if(entity instanceof MeleeAttack meleeAttack){
                        meleeAttack.meleeAttack(entity, entity1);
                    }
                    if(entity1 instanceof  MeleeAttack meleeAttack){
                        meleeAttack.meleeAttack(entity1, entity);
                    }
//                        System.out.println(entity.getIntersect(entity1));
                    //todo fix this : check for decrease hp
                    //todo : maybe incorrect circle intersect
//                    Point2D point2D = entity1.getIntersect(entity);
//                    System.out.println(entity1 + " " + entity + " " + point2D);
//                    try {
//
//                        impact(entity1.getIntersect(entity));
//                    }catch (Exception e){
//                        death();
//                    }
                }
            }
        }

        if(MotionSceneModel.getInstance().checkIntersect(EpsilonModel.getInstance())){
            impact(MotionSceneModel.getInstance().getIntersect(EpsilonModel.getInstance()));
        }


    }

    public void bulletIntersect(){
        ArrayList<EpsilonBulletModel> Bullets = new ArrayList<>();

        for(EpsilonBulletModel epsilonBulletModel : EpsilonBulletModel.epsilonBulletModels) {
            if (MotionSceneModel.getInstance().collision(epsilonBulletModel.getPosition())) {
                Point2D point2D = epsilonBulletModel.getPosition();
                if (point2D.getY() - MotionSceneModel.getInstance().getTop() <= 2)
                    MotionSceneModel.getInstance().change(0, -1);
                else if (MotionSceneModel.getInstance().getDown() - point2D.getY() <= 2)
                    MotionSceneModel.getInstance().change(0, 1);
                else if (point2D.getX() - MotionSceneModel.getInstance().getLeft() <= 2)
                    MotionSceneModel.getInstance().change(-1, 0);
                else if (MotionSceneModel.getInstance().getRight() - point2D.getX() <= 2)
                    MotionSceneModel.getInstance().change(1, 0);
                Bullets.add(epsilonBulletModel);
                impact(epsilonBulletModel.getPosition());
            }
            for(TriangleModel triangleModel : TriangleModel.triangleModels){
                if(triangleModel.collision(epsilonBulletModel.getPosition())){
                    Bullets.add(epsilonBulletModel);
                    impact(epsilonBulletModel.getPosition());
                    triangleModel.decreaseHearth(BULLET_DECRASE_HEALTH);
                }
            }
            for(SquareModel squareModel : SquareModel.squareModels){
                if(squareModel.collision(epsilonBulletModel.getPosition())){
                    Bullets.add(epsilonBulletModel);
                    impact(epsilonBulletModel.getPosition());
                    squareModel.decreaseHearth(BULLET_DECRASE_HEALTH);
                }
            }
        }

        for(EpsilonBulletModel epsilonBulletModel : Bullets)
            epsilonBulletModel.remove();
    }

    public void collectableGet(){
        ArrayList<CollectableItemModel> collectableItemModels = new ArrayList<>();
        for(CollectableItemModel collectableItemModel : CollectableItemModel.collectableItemModels){
            double x = collectableItemModel.getPosition().getX();
            double y = collectableItemModel.getPosition().getY();
            double dis = (EpsilonModel.getInstance().getX() - x) * (EpsilonModel.getInstance().getX() - x) +
                    (EpsilonModel.getInstance().getY() - y) * (EpsilonModel.getInstance().getY() - y);
            if(dis <= EpsilonModel.getInstance().getRadius() * EpsilonModel.getInstance().getRadius()) {
                collectableItemModels.add(collectableItemModel);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() + 5);
            }
            if(System.currentTimeMillis() - collectableItemModel.getTime() >= 10000)
                collectableItemModels.add(collectableItemModel);
        }
        for(CollectableItemModel collectableItemModel : collectableItemModels)
            collectableItemModel.remove();

    }

    public void move(){
        for(SquareModel squareModel : SquareModel.squareModels) {
            Direction direction = new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(), squareModel.getPosition()));
            if(Math.random() * 100 < 0.1 && squareModel.getAcceleration() == 0){
                double C = 0.0003;
                double acc = ACCELERATION;
                double dis = squareModel.getDistance(EpsilonModel.getInstance().getPosition(), squareModel.getPosition());
                if(dis <= 1)acc /= C;
                else acc /= (dis * C);
                squareModel.setAcceleration(acc);
                squareModel.setImpactDirection(direction);
            }
            squareModel.move(direction);
        }
        for(TriangleModel triangleModel : TriangleModel.triangleModels) {
            Direction direction = new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(), triangleModel.getPosition()));
            direction = new Direction(Utils.multiplyVector(direction.getDirectionVector(),
                    (triangleModel.getDistance(triangleModel.getPosition(), EpsilonModel.getInstance().getPosition())) > 100 ? 1.7 : 1));
            triangleModel.move(direction);
        }


        if(inputManager.isUp())
            EpsilonModel.getInstance().change(new Direction(270));
        if(inputManager.isDown())
            EpsilonModel.getInstance().change(new Direction(90));
        if(inputManager.isLeft())
            EpsilonModel.getInstance().change(new Direction(180));
        if(inputManager.isRight())
            EpsilonModel.getInstance().change(new Direction(0));
    }

    private void update(){
        EpsilonModel.getInstance().update();
        for(SquareModel squareModel : SquareModel.squareModels)
            squareModel.update();
        for(TriangleModel triangleModel : TriangleModel.triangleModels)
            triangleModel.update();
        for(EpsilonBulletModel epsilonBulletModel : EpsilonBulletModel.epsilonBulletModels)
            epsilonBulletModel.update();

        MotionSceneModel.getInstance().update();
//        MotionSceneView.getInstance().paint();
    }
    private void impact(Point2D point2D){
        double C = 0.0003;
        for(SquareModel squareModel : SquareModel.squareModels){
            double dis = squareModel.getDistance(squareModel.getPosition(), point2D);
            if(dis <= 25)squareModel.playAcceleration(point2D, ACCELERATION / C);
            else squareModel.playAcceleration(point2D, ACCELERATION / (dis*dis * C));
        }
        for(TriangleModel triangleModel : TriangleModel.triangleModels){
            double dis = triangleModel.getDistance(triangleModel.getPosition(), point2D);
            if(dis <= 25)triangleModel.playAcceleration(point2D, ACCELERATION / C);
            else triangleModel.playAcceleration(point2D, ACCELERATION / (dis*dis * C));
        }
        EpsilonModel model = EpsilonModel.getInstance();
        double dis = model.getDistance(model.getPosition(), point2D);
        if(dis <= 25)model.playAcceleration(point2D, ACCELERATION / (C * 1));
        else model.playAcceleration(point2D, ACCELERATION / (dis*dis * C * 1));
    }
    private void death(){
        finish();
        stage.setScene(SceneLoader.getGameOver());
    }
    private void win(){
        finish();

//todo

        stage.setScene(SceneLoader.getMenu());
    }

    private void finish(){
        SquareModel.squareModels.clear();
        SquareView.squareViews.clear();
        TriangleModel.triangleModels.clear();
        TriangleView.triangleViews.clear();
        CollectableItemModel.collectableItemModels.clear();
        CollectableItemView.collectableItemViews.clear();
        MotionSceneModel.getInstance().reset();
//        EpsilonModel.getInstance().init();
//        update();

        updateModel.stop();
        updateView.stop();
        enemyBuilder.stop();

        stage.setX(SCREEN_WIDTH/2 - MAX_WIDTH/2);
        stage.setY(SCREEN_HEIGHT/2 - MAX_HEIGHT/2);
        stage.setWidth(MAX_WIDTH);
        stage.setHeight(MAX_HEIGHT);


    }

    public Update(){

        //TODO : clear all past things and make almost everything empty

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

        EpsilonModel.getInstance().init();
        TriangleModel.triangleModels.clear();
        SquareModel.squareModels.clear();
        EpsilonBulletModel.epsilonBulletModels.clear();
        CollectableItemModel.collectableItemModels.clear();
    }

    public void shoot(Point2D point2D){
        if(System.currentTimeMillis() - dalay > BULLET_DELAY) {
//            new BulletModel(point2D.getX() + stage.getX(), point2D.getY() + stage.getY());
            Point2D pos = new Point2D(EpsilonModel.getInstance().getX(), EpsilonModel.getInstance().getY());
            EpsilonBulletModel epsilonBulletModel = new EpsilonBulletModel(pos.getX(), pos.getY());
            epsilonBulletModel.setDirection(new Direction(Utils.relativeLocation(new Point2D(point2D.getX() + stage.getX(), point2D.getY() + stage.getY()),
                    pos)).getDirectionVector());
//            System.out.println(point2D.getX() + " " + point2D.getY());
            dalay = System.currentTimeMillis();
        }
    }
}
