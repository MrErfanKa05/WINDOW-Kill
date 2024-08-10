package game.windowkill.controller;

import game.windowkill.controller.builder.EnemyBuilder;
import game.windowkill.controller.loader.ConfigLoader;
import game.windowkill.controller.loader.SceneLoader;
import game.windowkill.model.attack.AreaOfEffect;
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
import game.windowkill.model.characterModel.property.EnemyOverlaps;
import game.windowkill.model.characterModel.property.EnemyProperty;
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
import static game.windowkill.controller.Utils.getDistance;
import static java.lang.Math.*;

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
                if(necroPick.getId().equals(necropickView.getId())) {
                    necropickView.setPosition(necroPick.getPosition());
                    necropickView.onGround = necroPick.onGround;
                }
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

        ArchmireView.areas.clear();
        ArchmireView.areas.addAll(Archmire.areas);

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
        ArrayList<Entity> entities = new ArrayList<>();
        for(TriangleModel triangleModel : TriangleModel.triangleModels)
            if(triangleModel.getHp() <= 0) {
                enemyBuilder.enemyDead();
                entities.add(triangleModel);
                new CollectableItemModel(triangleModel.getPosition(), 5, triangleModel);
                new CollectableItemModel(Utils.addVector(triangleModel.getPosition(), new Point2D(5, 2)), 5, triangleModel);
            }
        for(SquareModel squareModel : SquareModel.squareModels)
            if(squareModel.getHp() <= 0) {
                enemyBuilder.enemyDead();
                entities.add(squareModel);
                new CollectableItemModel(squareModel.getPosition(), 5, squareModel);
            }
        int C = 7;
        for(Wyrm model : Wyrm.wyrms)
            if(model.getHp() <= 0){
                enemyBuilder.enemyDead();
                entities.add(model);
                for(int i = 0; i < model.getCollectables(); i++)
                    new CollectableItemModel(Utils.addVector(model.getPosition(), new Point2D((i/3) * C, (i%3) * C)), 3, model);
            }
        for(NecroPick model : NecroPick.necroPicks)
            if(model.getHp() <= 0){
                enemyBuilder.enemyDead();
                entities.add(model);
                for(int i = 0; i < model.getCollectables(); i++)
                    new CollectableItemModel(Utils.addVector(model.getPosition(), new Point2D((i/3) * C, (i%3) * C)), 3, model);
            }
        for(Omenoct model : Omenoct.omenocts)
            if(model.getHp() <= 0){
                enemyBuilder.enemyDead();
                entities.add(model);
                for(int i = 0; i < model.getCollectables(); i++)
                    new CollectableItemModel(Utils.addVector(model.getPosition(), new Point2D((i/3) * C, (i%3) * C)), 3, model);
            }
        for(Archmire model : Archmire.archmires)
            if(model.getHp() <= 0){
                enemyBuilder.enemyDead();
                entities.add(model);
                for(int i = 0; i < model.getCollectables(); i++)
                    new CollectableItemModel(Utils.addVector(model.getPosition(), new Point2D((i/3) * C, (i%3) * C)), 3, model);
            }
        for(BlackOrb model : BlackOrb.blackOrbs)
            if(model.getHp() <= 0){
                enemyBuilder.enemyDead();
                entities.add(model);
                for(int i = 0; i < model.getCollectables(); i++)
                    new CollectableItemModel(Utils.addVector(model.getPosition(), new Point2D((i/3) * C, (i%3) * C)), 3, model);
            }
        for(Barricados model : Barricados.barricados)
            if(model.getHp() <= 0){
                enemyBuilder.enemyDead();
                entities.add(model);
                for(int i = 0; i < model.getCollectables(); i++)
                    new CollectableItemModel(Utils.addVector(model.getPosition(), new Point2D((i/3) * C, (i%3) * C)), 3, model);
            }


        for(Entity entity : entities)
            entity.remove();

        if(EpsilonModel.getInstance().getHp() <= 0)death();
    }

    public void entityIntersect(){
        for(Entity entity : Entity.entities) {
            for (Entity entity1 : Entity.entities) {
                if (entity.getId().equals(entity1.getId()) || getDistance(entity1.getPosition(), entity.getPosition()) >= 100)
                    continue;
                if (entity.checkIntersect(entity1)) {
                    if(entity instanceof NecroPick necroPick)
                        if(!necroPick.onGround)
                            continue;
                    if(entity1 instanceof NecroPick necroPick)
                        if(!necroPick.onGround)
                            continue;

                    if(entity instanceof EpsilonModel epsilonModel && !(entity1 instanceof Wyrm) && !(entity1 instanceof Archmire)
                            && !(entity1 instanceof NecroPick) && !(entity1 instanceof Barricados)){
                        epsilonModel.meleeAttack(entity1, entity);
                    }
                    if(entity.enemyOverlaps!=EnemyOverlaps.Hovering && entity1.enemyOverlaps!=EnemyOverlaps.Hovering)
                        impact(entity.getIntersect(entity1));
                    if(entity instanceof EpsilonModel model && entity1.enemyProperty==EnemyProperty.NonRigid && entity1.enemyOverlaps==EnemyOverlaps.NonHovering){
                        Point2D point2D = Utils.relativeLocation(EpsilonModel.getInstance().getPosition(),
                                entity.getIntersect(entity1));
                        point2D = Utils.multiplyVector(point2D, 1);
                        double left = MotionSceneModel.getInstance().getLeft();
                        double right = MotionSceneModel.getInstance().getRight();
                        double top = MotionSceneModel.getInstance().getTop();
                        double down = MotionSceneModel.getInstance().getDown();
                        Point2D point = model.getPosition();
                        if(point2D.getY() > 0 && point2D.getX() > 0)
                            entity.setPosition(Utils.addVector(entity.getPosition(), point2D));
                        if(model.getX() - model.getRadius() > left && model.getX() + model.getRadius() < right &&
                                model.getY() - model.getRadius() > top && model.getY() + model.getRadius() < down) {
                        }
                        else model.setPosition(point);
                    }
                    else if(entity.enemyOverlaps==EnemyOverlaps.NonHovering && entity1.enemyOverlaps==EnemyOverlaps.NonHovering){
                        Point2D point2D = Utils.relativeLocation(entity.getPosition(),
                                entity.getIntersect(entity1));
                        point2D = Utils.multiplyVector(point2D, 1);
                        if(point2D.getY() > 0 && point2D.getX() > 0)
                            entity.setPosition(Utils.addVector(entity.getPosition(), point2D));
                    }
                    if(!(entity1 instanceof Archmire) && entity instanceof Archmire archmire){
                        archmire.drown(entity1);
                    }

                    if(entity instanceof Wyrm wyrm){
                        wyrm.changeSide();
                    }
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
                if (point2D.getY() - MotionSceneModel.getInstance().getTop() <= 2) {
                    MotionSceneModel.getInstance().change(0, -1);

                    for(Omenoct omenoct : Omenoct.omenocts)
                        if(omenoct.isTouchScene)
                            if(omenoct.isTop())
                                omenoct.setHp(omenoct.getHp() - 1);
                }
                else if (MotionSceneModel.getInstance().getDown() - point2D.getY() <= 2) {
                    MotionSceneModel.getInstance().change(0, 1);
                    for(Omenoct omenoct : Omenoct.omenocts)
                        if(omenoct.isTouchScene)
                            if(omenoct.isDown())
                                omenoct.setHp(omenoct.getHp() - 1);
                }
                else if (point2D.getX() - MotionSceneModel.getInstance().getLeft() <= 2) {
                    MotionSceneModel.getInstance().change(-1, 0);
                    for(Omenoct omenoct : Omenoct.omenocts)
                        if(omenoct.isTouchScene)
                            if(omenoct.isLeft())
                                omenoct.setHp(omenoct.getHp() - 1);
                }
                else if (MotionSceneModel.getInstance().getRight() - point2D.getX() <= 2){
                    MotionSceneModel.getInstance().change(1, 0);
                    for(Omenoct omenoct : Omenoct.omenocts)
                        if(omenoct.isTouchScene)
                            if(omenoct.isRight())
                                omenoct.setHp(omenoct.getHp() - 1);
                }
                Bullets.add(epsilonBulletModel);
                impact(epsilonBulletModel.getPosition());
            }
            for(Entity entity : Entity.entities){
                if(entity instanceof EpsilonModel)
                    continue;
                if(entity instanceof NecroPick necroPick)
                    if(!necroPick.onGround)
                        continue;
                if(entity.collision(epsilonBulletModel.getPosition())){
                    Bullets.add(epsilonBulletModel);
                    impact(epsilonBulletModel.getPosition());
                    entity.decreaseHearth(BULLET_DECRASE_HEALTH);
                }
            }
        }

        for(EpsilonBulletModel epsilonBulletModel : Bullets)
            epsilonBulletModel.remove();

        ArrayList<EnemyBulletModel> enemyBulletModels = new ArrayList<>();

        for(EnemyBulletModel enemyBulletModel : EnemyBulletModel.enemyBulletModels){
            if(EpsilonModel.getInstance().collision(enemyBulletModel.getPosition())){
                enemyBulletModels.add(enemyBulletModel);
                impact(enemyBulletModel.getPosition());
                EpsilonModel.getInstance().decreaseHearth(BULLET_DECRASE_HEALTH);
            }
            if(enemyBulletModel.getPosition().getY() < 0 || enemyBulletModel.getPosition().getY() > SCREEN_HEIGHT
                || enemyBulletModel.getPosition().getX() < 0 || enemyBulletModel.getPosition().getX() > SCREEN_WIDTH)
                enemyBulletModels.add(enemyBulletModel);
        }

        for(EnemyBulletModel model : enemyBulletModels)
            model.remove();
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
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() + collectableItemModel.getXp());
            }
            if(System.currentTimeMillis() - collectableItemModel.getTime() >= 10000)
                collectableItemModels.add(collectableItemModel);
        }
        for(CollectableItemModel collectableItemModel : collectableItemModels)
            collectableItemModel.remove();

    }

    public void move(){
        for(SquareModel squareModel : SquareModel.squareModels) {
            Direction direction = new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(),
                    Utils.addVector(squareModel.getPosition(), new Point2D(SQUARE_OUTSIDE/2, SQUARE_OUTSIDE/2))));
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
            Direction direction = new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(),
                    Utils.addVector(triangleModel.getPosition(), new Point2D(SQUARE_OUTSIDE/2, SQUARE_OUTSIDE/2))));
            direction = new Direction(Utils.multiplyVector(direction.getDirectionVector(),
                    (triangleModel.getDistance(triangleModel.getPosition(), EpsilonModel.getInstance().getPosition())) > 100 ? 1.7 : 1));
            triangleModel.move(direction);
        }
        for(NecroPick necroPick : NecroPick.necroPicks) {
            if (necroPick.onGround) {
                if (System.currentTimeMillis() - necroPick.time >= 8000) {
                    necroPick.onGround = false;
                    necroPick.time = System.currentTimeMillis();
                }
            } else {
                if (System.currentTimeMillis() - necroPick.time >= 4000) {
                    necroPick.onGround = true;
                    necroPick.time = System.currentTimeMillis();
                    for (int i = 0; i < 8; i++) {
                        EnemyBulletModel model = new EnemyBulletModel(necroPick.getPosition().getX() + BIG_ENEMY_OUTSIDE / 2,
                                necroPick.getPosition().getY() + BIG_ENEMY_OUTSIDE / 2, necroPick.getRangeAttackHp());
                        model.setDirection(new Direction(45 * i + 1).getDirectionVector());

                    }
                }
            }
        }

        for(Omenoct omenoct : Omenoct.omenocts){
            BaseSceneModel model = EpsilonModel.getInstance().getBaseSceneModel();
            double d1 = getDistance(omenoct.getPosition(), new Point2D(model.getLeft(), model.getDown()/2 + model.getTop()/2));
            double d2 = getDistance(Utils.addVector(omenoct.getPosition(), new Point2D(BIG_ENEMY_OUTSIDE, 0)), new Point2D(model.getRight(), model.getDown()/2 + model.getTop()/2));
            double d3 = getDistance(omenoct.getPosition(), new Point2D(model.getRight()/2 + model.getLeft()/2, model.getTop()));
            double d4 = getDistance(Utils.addVector(omenoct.getPosition(), new Point2D(0, BIG_ENEMY_OUTSIDE)), new Point2D(model.getRight()/2 + model.getLeft()/2, model.getDown()));
            double mx = min(d1, min(d2, min(d3, d4)));
            Point2D nextPath = null;
            if(mx == d1){
                nextPath = new Point2D(model.getLeft(), model.getDown()/2 + model.getTop()/2);
                omenoct.setLeft();
            }
            if(mx == d2){
                nextPath = new Point2D(model.getRight() - BIG_ENEMY_OUTSIDE, model.getDown()/2 + model.getTop()/2);
                omenoct.setRight();
            }
            if(mx == d3){
                nextPath = new Point2D(model.getRight()/2 + model.getLeft()/2, model.getTop());
                omenoct.setTop();
            }
            if(mx == d4){
                nextPath = new Point2D(model.getRight()/2 + model.getLeft()/2, model.getDown() - BIG_ENEMY_OUTSIDE);
                omenoct.setDown();
            }
            if(mx <= 2){
                omenoct.isTouchScene = true;
                if(System.currentTimeMillis() - omenoct.time > 3000){
                    enemyShoot(omenoct.getPosition(), omenoct.getRangeAttackHp());
                    omenoct.time = System.currentTimeMillis();
                }
            }
            else {
                omenoct.isTouchScene = false;
            }
            omenoct.setDirection(new Direction(Utils.relativeLocation(nextPath, omenoct.getPosition())).getDirectionVector());
        }

        for(Archmire archmire : Archmire.archmires){
            archmire.setDirection(new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(),
                    Utils.addVector(archmire.getPosition(), new Point2D(BIG_ENEMY_OUTSIDE/2, BIG_ENEMY_OUTSIDE/2)))).getDirectionVector());
        }

        for(Wyrm wyrm : Wyrm.wyrms){
            if(getDistance(wyrm.getPosition(), EpsilonModel.getInstance().getPosition()) >= WYRM_MINIMUM_DISTANCE)
                wyrm.setDirection(new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(), wyrm.getPosition())).getDirectionVector());
            else {
                if(System.currentTimeMillis() - wyrm.time > 3000){
                    enemyShoot(wyrm.getPosition(), wyrm.getRangeAttackHp());
                    wyrm.time = System.currentTimeMillis();
                }
                Direction direction = new Direction(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(), wyrm.getPosition()));
                Point2D circlePath = direction.getAdd90();
                Direction path = new Direction(Utils.multiplyVector(circlePath, wyrm.getSide()));
                wyrm.setDirection(path.getDirectionVector());
            }
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
//            setPosition();
            win();
        }
        if(EnemyBuilder.wave == 4){
            updateModel.play();
            setPosition();
        }

        ArrayList<EnemyBulletModel> enemyBulletModels = new ArrayList<>();

        for(EnemyBulletModel enemyBulletModel : EnemyBulletModel.enemyBulletModels)
            enemyBulletModels.add(enemyBulletModel);

        for(EnemyBulletModel model : enemyBulletModels)
            model.remove();

        ArrayList<EpsilonBulletModel> epsilonBulletModels = new ArrayList<>();

        for(EpsilonBulletModel epsilonBulletModel : EpsilonBulletModel.epsilonBulletModels)
            epsilonBulletModels.add(epsilonBulletModel);

        for(EpsilonBulletModel model : epsilonBulletModels)
            model.remove();

        enemyBuilder.finishWave();
        EpsilonModel.getInstance().setRadius(prevRadius);
    }

    private double prevRadius = 0;

    public void waveFinisher(){

        waveChanger.setCycleCount(Animation.INDEFINITE);
        updateModel.pause();
        prevRadius = EpsilonModel.getInstance().getRadius();
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
        for(EnemyBulletModel enemyBulletModel : EnemyBulletModel.enemyBulletModels)
            enemyBulletModel.update();
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
        AreaOfEffect.deleteArea();

        MotionSceneModel.getInstance().update();
//        MotionSceneView.getInstance().paint();
    }
    private void impact(Point2D point2D, double C){
        for(Entity model : Entity.entities){
            if(model instanceof EpsilonModel)
                continue;
            if(model.enemyOverlaps==EnemyOverlaps.Hovering || model.enemyProperty== EnemyProperty.Rigid)
                continue;
            double dis = getDistance(model.getPosition(), point2D);
            if(dis <= 25)model.playAcceleration(point2D, ACCELERATION / (C));
            else model.playAcceleration(point2D, ACCELERATION / (dis/2 * C));
        }
        EpsilonModel model = EpsilonModel.getInstance();
        Point2D position = model.getPosition();
        double dis = model.getDistance(model.getPosition(), point2D);
        if(dis <= 25)model.playAcceleration(point2D, ACCELERATION / (C));
        else model.playAcceleration(point2D, ACCELERATION / (dis * C * 1));
    }
    private void impact(Point2D point2D){
        double C = 0.4;
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

        stage.setScene(SceneLoader.getWin());
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
        MotionSceneView.getInstance().setElapsedTimeInt(enemyBuilder.totalTime);
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

        ConfigLoader.getInstance().saveRound(EpsilonModel.getInstance().getXP() - EpsilonModel.getInstance().getLastXP(), enemyBuilder.totalTime);

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

        EpsilonModel.getInstance().init();
        TriangleModel.triangleModels.clear();
        SquareModel.squareModels.clear();
        EpsilonBulletModel.epsilonBulletModels.clear();
        CollectableItemModel.collectableItemModels.clear();
        EnemyBulletModel.enemyBulletModels.clear();
        NecroPick.necroPicks.clear();
        Archmire.archmires.clear();
        Omenoct.omenocts.clear();
        Wyrm.wyrms.clear();
        BlackOrb.blackOrbs.clear();
        Barricados.barricados.clear();
        Archmire.areas.clear();
        Archmire.drownTimes.clear();
        Archmire.times.clear();
        IsoNonRigidScene.isoNonRigidScenes.clear();
        IsoRigidScene.isoRigidScenes.clear();
        NonIsoRigidScene.nonIsoRigidScenes.clear();
        NonIsoNonRigidScene.nonIsoNonRigidScenes.clear();

        for(NonIsoRigidSceneView view : NonIsoRigidSceneView.nonIsoRigidSceneViews)
            view.stage.close();
        for(NonIsoNonRigidSceneView view : NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews)
            view.stage.close();
        for(IsoRigidSceneView view : IsoRigidSceneView.isoRigidSceneViews)
            view.stage.close();
        for(IsoNonRigidSceneView view : IsoNonRigidSceneView.isoNonRigidSceneViews)
            view.stage.close();

        NonIsoNonRigidSceneView.nonIsoNonRigidSceneViews.clear();
        NonIsoRigidSceneView.nonIsoRigidSceneViews.clear();
        IsoNonRigidSceneView.isoNonRigidSceneViews.clear();
        IsoRigidSceneView.isoRigidSceneViews.clear();
        NecropickView.necropickViews.clear();
        OmenoctView.omenoctViews.clear();
        ArchmireView.areas.clear();
        ArchmireView.archmireViews.clear();
        WyrmView.wyrmViews.clear();
        BarricadosView.barricadosViews.clear();
        BlackOrbView.blackOrbViews.clear();
        CollectableItemView.collectableItemViews.clear();
        EnemyBulletView.enemyBulletViews.clear();
        EpsilonBulletView.epsilonBulletViews.clear();
        TriangleView.triangleViews.clear();
        SquareView.squareViews.clear();


        updateModel.play();
        updateView.play();
    }

    public void shoot(Point2D point2D){
        if(System.currentTimeMillis() - delay > BULLET_DELAY) {
            Point2D pos = new Point2D(EpsilonModel.getInstance().getX(), EpsilonModel.getInstance().getY());
            EpsilonBulletModel epsilonBulletModel = new EpsilonBulletModel(pos.getX(), pos.getY(), EpsilonModel.getInstance().getRangeAttackHp());
            epsilonBulletModel.setDirection(new Direction(Utils.relativeLocation(new Point2D(point2D.getX() + stage.getX(), point2D.getY() + stage.getY()),
                    pos)).getDirectionVector());
            delay = System.currentTimeMillis();
        }
    }
    public void enemyShoot(Point2D point2D, int rangeAttackHp){
        EnemyBulletModel model = new EnemyBulletModel(point2D.getX() + BIG_ENEMY_OUTSIDE/2, point2D.getY() + BIG_ENEMY_OUTSIDE/2, rangeAttackHp);
        model.setDirection(Utils.relativeLocation(EpsilonModel.getInstance().getPosition(), Utils.addVector(point2D, new Point2D(BIG_ENEMY_OUTSIDE/2, BIG_ENEMY_OUTSIDE/2))));
    }
}
