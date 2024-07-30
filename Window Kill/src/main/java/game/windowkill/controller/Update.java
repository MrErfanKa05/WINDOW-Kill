package game.windowkill.controller;

import game.windowkill.controller.builder.EnemyBuilder;
import game.windowkill.controller.loader.SceneLoader;
import game.windowkill.model.bullets.EnemyBulletModel;
import game.windowkill.model.bullets.EpsilonBulletModel;
import game.windowkill.model.CollectableItemModel;
import game.windowkill.model.attack.MeleeAttack;
import game.windowkill.model.characterModel.CerberusBallModel;
import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.SkillState;
import game.windowkill.model.characterModel.enemy.*;
import game.windowkill.model.characterModel.miniBoss.Barricados;
import game.windowkill.model.characterModel.miniBoss.BlackOrb;
import game.windowkill.model.sceneModel.*;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.move.Direction;
import game.windowkill.view.bullet.CerberusBulletView;
import game.windowkill.view.bullet.EnemyBulletView;
import game.windowkill.view.bullet.EpsilonBulletView;
import game.windowkill.view.CollectableItemView;
import game.windowkill.view.characterView.enemy.*;
import game.windowkill.view.characterView.miniBoss.BarricadosView;
import game.windowkill.view.characterView.miniBoss.BlackOrbView;
import game.windowkill.view.scene.*;
import game.windowkill.view.characterView.EpsilonView;
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
    private double delay = 0;
    private boolean gameFinish = false;
    public static boolean bigImpact = false, bigShot = false, stopGame = false, farEnemy = false, multiShot;
    public static ArrayList<SkillState> onSkillStates = new ArrayList<>();
    Timeline updateView = new Timeline(new KeyFrame(Duration.millis(FRAME_UPDATE_TIME * 1000), actionEvent -> {
        MotionSceneModel model = MotionSceneModel.getInstance();
        EpsilonView.getInstance().setPosition(EpsilonModel.getInstance().getPosition());
        EpsilonView.getInstance().setRadius(EpsilonModel.getInstance().getRadius());

        for(EpsilonBulletView epsilonBulletView : EpsilonBulletView.epsilonBulletViews){
            for(EpsilonBulletModel epsilonBulletModel : EpsilonBulletModel.epsilonBulletModels) {
                if (epsilonBulletModel.getId().equals(epsilonBulletView.getId())) {
                    epsilonBulletView.setPosition(epsilonBulletModel.getPosition());
                }
            }
        }

        for(EnemyBulletView enemyBulletView : EnemyBulletView.enemyBulletViews){
            for(EnemyBulletModel enemyBulletModel : EnemyBulletModel.enemyBulletModels){
                if(enemyBulletModel.getId().equals(enemyBulletView.getId()))
                    enemyBulletView.setPosition(enemyBulletModel.getPosition());
            }
        }

        for(CerberusBulletView cerberusBulletView : CerberusBulletView.cerberusBulletViews){
            for(CerberusBallModel cerberusBallModel : CerberusBallModel.cerberusBallModels){
                if(cerberusBallModel.getId().equals(cerberusBulletView.getId()))
                    cerberusBulletView.setPosition(cerberusBallModel.getPosition());
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

        for(OmenoctView omenoctView : OmenoctView.omenoctViews){
            for(Omenoct omenoct : Omenoct.omenocts)
                if(omenoct.getId().equals(omenoctView.getId()))
                    omenoctView.setPosition(omenoct.getPosition());
        }

        for(ArchmireView archmireView : ArchmireView.archmireViews){
            for(Archmire archmire : Archmire.archmires)
                if(archmire.getId().equals(archmireView.getId()))
                    archmireView.setPosition(archmire.getPosition());
        }

        for(NecropickView necropickView : NecropickView.necropickViews){
            for(NecroPick necroPick : NecroPick.necroPicks)
                if(necroPick.getId().equals(necropickView.getId()))
                    necropickView.setPosition(necroPick.getPosition());
        }

        for(WyrmView wyrmView : WyrmView.wyrmViews){
            for(Wyrm wyrm : Wyrm.wyrms)
                if(wyrm.getId().equals(wyrmView.getId()))
                    wyrmView.setPosition(wyrm.getPosition());
        }

        for(BarricadosView barricadosView : BarricadosView.barricadosViews){
            for(Barricados barricados : Barricados.barricados)
                if(barricados.getId().equals(barricadosView.getId()))
                    barricadosView.setPosition(barricados.getPosition());
        }

        for(BlackOrbView blackOrbView : BlackOrbView.blackOrbViews){
            for(BlackOrb blackOrb : BlackOrb.blackOrbs)
                if(blackOrb.getId().equals(blackOrbView.getId()))
                    blackOrbView.setPosition(blackOrb.getPosition());
        }

        for(NonIsoRigidSceneView view : NonIsoRigidSceneView.nonIsoRigidSceneViews){
            for(NonIsoRigidScene scene : NonIsoRigidScene.nonIsoRigidScenes)
                if(scene.getId().equals(view.getId()))
                    view.set(scene.getLeft(), scene.getRight(), scene.getTop(), scene.getDown());
        }

        for(NonIsoNonRigidSceneView view : NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews){
            for(NonIsoNonRigidScene scene : NonIsoNonRigidScene.nonIsoNonRigidScenes)
                if(scene.getId().equals(view.getId()))
                    view.set(scene.getLeft(), scene.getRight(), scene.getTop(), scene.getDown());
        }

        for(IsoNonRigidSceneView view : IsoNonRigidSceneView.isoNonRigidSceneViews){
            for(IsoNonRigidScene scene : IsoNonRigidScene.isoNonRigidScenes)
                if(scene.getId().equals(view.getId()))
                    view.set(scene.getLeft(), scene.getRight(), scene.getTop(), scene.getDown());
        }

        for(IsoRigidSceneView view : IsoRigidSceneView.isoRigidSceneViews){
            for(IsoRigidScene scene : IsoRigidScene.isoRigidScenes)
                if(scene.getId().equals(view.getId()))
                    view.set(scene.getLeft(), scene.getRight(), scene.getTop(), scene.getDown());
        }

        //todo : for motionSceneView : set right and left and... with epsilon.mainScene

        model = MotionSceneModel.getInstance();
        MotionSceneView.getInstance().set(model.getTop(), model.getDown(), model.getRight(), model.getLeft());
        MotionSceneView.getInstance().paint();

        for(IsoRigidSceneView view : IsoRigidSceneView.isoRigidSceneViews)
            view.paint();
        for(IsoNonRigidSceneView view : IsoNonRigidSceneView.isoNonRigidSceneViews)
            view.paint();
        for(NonIsoNonRigidSceneView view : NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews)
            view.paint();
        for(NonIsoRigidSceneView view : NonIsoRigidSceneView.nonIsoRigidSceneViews)
            view.paint();

    }));
    Timeline updateModel = new Timeline(new KeyFrame(Duration.millis(MODEL_UPDATE_TIME * 1000), actionEvent -> {
        checkShop();

        checkAbility();

        playWave();

        update();

        bulletIntersect();

        entityIntersect();

        entityDeath();

        collectableGet();

        move();

//        System.out.println(EpsilonModel.getInstance().getPosition());

    }));

    public void checkShop(){

    }

    public void checkAbility(){

    }

    public void playWave(){
        if(enemyBuilder.waveFinish){
            switch (enemyBuilder.wave) {
                case 12:
                    waveFinisher();
                    break;
                case 11:
                    enemyBuilder.playWave11();
                    break;
                case 10:
                    stopWaveChanger();
                case 9:
                    enemyBuilder.playWave9();
                    break;
                case 8:
                    enemyBuilder.playWave8();
                    break;
                case 7:
                    enemyBuilder.playWave7();
                    break;
                case 6:
                    enemyBuilder.playWave6();
                    break;
                case 5:
                    enemyBuilder.playWave5();
                    break;
                case 4:
                    waveFinisher();
                    break;
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
                if (entity.checkIntersect(entity1)) {
                    //todo : check for the globalRouting and local
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

//        if(MotionSceneModel.getInstance().checkIntersect(EpsilonModel.getInstance())){
//            impact(MotionSceneModel.getInstance().getIntersect(EpsilonModel.getInstance()));
//        }


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

    private Timeline waveChanger = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
        EpsilonModel.getInstance().setRadius(EpsilonModel.getInstance().getRadius() + 3);

        MotionSceneModel.getInstance().setLeft(MotionSceneModel.getInstance().getLeft() + 3);
        MotionSceneModel.getInstance().setTop(MotionSceneModel.getInstance().getTop() + 3);
        MotionSceneModel.getInstance().setRight(MotionSceneModel.getInstance().getRight() - 3);
        MotionSceneModel.getInstance().setDown(MotionSceneModel.getInstance().getDown() - 3);
        if(max(MotionSceneModel.getInstance().getRight() - MotionSceneModel.getInstance().getLeft(),
                MotionSceneModel.getInstance().getDown() - MotionSceneModel.getInstance().getTop()) <= 5)
            stopWaveChanger();
    }));

    private void stopWaveChanger(){
        waveChanger.stop();
        if(EnemyBuilder.wave == 12)
            win();
        if(EnemyBuilder.wave == 10){
            setPosition();
        }
        if(EnemyBuilder.wave == 4){
            updateModel.play();
            setPosition();
        }

        for(EnemyBulletModel enemyBulletModel : EnemyBulletModel.enemyBulletModels)
            enemyBulletModel.remove();
        for(EpsilonBulletModel epsilonBulletModel : EpsilonBulletModel.epsilonBulletModels)
            epsilonBulletModel.remove();

    }

    public void waveFinisher(){

        waveChanger.setCycleCount(Animation.INDEFINITE);
        updateModel.pause();
        waveChanger.play();

        //todo : make everything ok before and after this
    }

    private void update(){
        EpsilonModel.getInstance().update();
        for(SquareModel squareModel : SquareModel.squareModels)
            squareModel.update();
        for(TriangleModel triangleModel : TriangleModel.triangleModels)
            triangleModel.update();
        for(EpsilonBulletModel epsilonBulletModel : EpsilonBulletModel.epsilonBulletModels)
            epsilonBulletModel.update();
        for(Archmire archmire : Archmire.archmires)
            archmire.update();
        for(NecroPick necroPick : NecroPick.necroPicks)
            necroPick.update();
        for(Omenoct omenoct : Omenoct.omenocts)
            omenoct.update();
        for(Wyrm wyrm : Wyrm.wyrms)
            wyrm.update();
        for(Barricados barricados : Barricados.barricados)
            barricados.update();
        for(BlackOrb blackOrb : BlackOrb.blackOrbs)
            blackOrb.update();
        for(IsoRigidScene scene : IsoRigidScene.isoRigidScenes)
            scene.update();
        for(NonIsoNonRigidScene scene : NonIsoNonRigidScene.nonIsoNonRigidScenes)
            scene.update();
        for(NonIsoRigidScene scene : NonIsoRigidScene.nonIsoRigidScenes)
            scene.update();
        for(IsoNonRigidScene scene : IsoNonRigidScene.isoNonRigidScenes)
            scene.update();

        MotionSceneModel.getInstance().update();
//        MotionSceneView.getInstance().paint();
    }
    private void impact(Point2D point2D, double C){
        for(SquareModel squareModel : SquareModel.squareModels){
            double dis = squareModel.getDistance(squareModel.getPosition(), point2D);
            if(dis <= 25)squareModel.playAcceleration(point2D, ACCELERATION / (C * dis));
            else squareModel.playAcceleration(point2D, ACCELERATION / (dis*dis * C));
        }
        for(TriangleModel triangleModel : TriangleModel.triangleModels){
            double dis = triangleModel.getDistance(triangleModel.getPosition(), point2D);
            if(dis <= 25)triangleModel.playAcceleration(point2D, ACCELERATION / (C * dis));
            else triangleModel.playAcceleration(point2D, ACCELERATION / (dis*dis * C));
        }
        EpsilonModel model = EpsilonModel.getInstance();
        double dis = model.getDistance(model.getPosition(), point2D);
        if(dis <= 25)model.playAcceleration(point2D, ACCELERATION / (dis * C * 1));
        else model.playAcceleration(point2D, ACCELERATION / (dis*dis * C * 1));
    }
    private void impact(Point2D point2D){
        double C = 0.003;
        impact(point2D, C);
    }
    private void bigImpact(Point2D point2D){
        double C = 0.0007;
        impact(point2D, C);
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
        gameFinish = true;
        SquareModel.squareModels.clear();
        SquareView.squareViews.clear();
        TriangleModel.triangleModels.clear();
        TriangleView.triangleViews.clear();
        CollectableItemModel.collectableItemModels.clear();
        CollectableItemView.collectableItemViews.clear();
        MotionSceneModel.getInstance().reset();
//        EpsilonModel.getInstance().init();
        //todo : end the thread
//        update();

        updateModel.stop();
        updateView.stop();
        enemyBuilder.stop();

        stage.setX(SCREEN_WIDTH/2 - MAX_WIDTH/2);
        stage.setY(SCREEN_HEIGHT/2 - MAX_HEIGHT/2);
        stage.setWidth(MAX_WIDTH);
        stage.setHeight(MAX_HEIGHT);

    }

    public void setPosition(){
        MotionSceneModel.getInstance().reset();

        stage.setHeight(MAX_HEIGHT);
        stage.setWidth(MAX_WIDTH);
        stage.setX(SCREEN_WIDTH/2 - (MotionSceneModel.getInstance().getRight() - MotionSceneModel.getInstance().getLeft())/2);
        stage.setY(SCREEN_HEIGHT/2 - (MotionSceneModel.getInstance().getDown() - MotionSceneModel.getInstance().getTop())/2);

        EpsilonModel.getInstance().setPosition(new Point2D(SCREEN_WIDTH/2, SCREEN_HEIGHT/2));
    }

    public Update(){

        //TODO : clear all past things and make almost everything empty

        gameFinish = false;

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
        if(System.currentTimeMillis() - delay > BULLET_DELAY) {
//            new BulletModel(point2D.getX() + stage.getX(), point2D.getY() + stage.getY());
            Point2D pos = new Point2D(EpsilonModel.getInstance().getX(), EpsilonModel.getInstance().getY());
            EpsilonBulletModel epsilonBulletModel = new EpsilonBulletModel(pos.getX(), pos.getY(), EpsilonModel.getInstance().getRangeAttackHp());
            epsilonBulletModel.setDirection(new Direction(Utils.relativeLocation(new Point2D(point2D.getX() + stage.getX(), point2D.getY() + stage.getY()),
                    pos)).getDirectionVector());
//            System.out.println(point2D.getX() + " " + point2D.getY());
            delay = System.currentTimeMillis();
        }
    }
}
