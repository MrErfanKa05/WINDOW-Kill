package game.windowkill.controller.builder;

import game.windowkill.controller.Constant;
import game.windowkill.controller.DataHolder;
import game.windowkill.controller.Utils;
import game.windowkill.model.characterModel.enemy.*;
import game.windowkill.model.characterModel.miniBoss.Barricados;
import game.windowkill.model.characterModel.miniBoss.BlackOrb;
import game.windowkill.model.sceneModel.IsoNonRigidScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

import static game.windowkill.controller.Constant.*;
import static game.windowkill.controller.DataHolder.difficulty;

public class EnemyBuilder {
    private int wave1Triangle, wave1Square, wave2Triangle, wave2Square, wave3Triangle, wave3Square;
    private int CWave1Triangle, CWave1Square, CWave2Triangle, CWave2Square, CWave3Triangle, CWave3Square;
    private int totalTime = 0, w1Time = 0, w2Time = 0, w3Time = 0, w5Time = 0, w6Time = 0, w7Time = 0, w8Time = 0, w9Time = 0, w11Time = 0;
    private Random random = new Random();
    public static int wave = 1;
    public boolean waveFinish = true;
    public EnemyBuilder(){
        this((int) (2 + difficulty/100 * 3), (int) (3 + difficulty/100 * 3), (int) (4 + difficulty/100 * 4), (int) (4 + difficulty/100 * 4), (int) (5 + difficulty/100 * 5), (int) (5+ difficulty/100 * 5));
    }
    public EnemyBuilder(int wave1Triangle, int wave1Square, int wave2Triangle, int wave2Square, int wave3Triangle, int wave3Square) {
        this.wave1Triangle = wave1Triangle;
        this.wave1Square = wave1Square;
        this.wave2Triangle = wave2Triangle;
        this.wave2Square = wave2Square;
        this.wave3Triangle = wave3Triangle;
        this.wave3Square = wave3Square;
        this.CWave1Triangle = wave1Triangle;
        this.CWave1Square = wave1Square;
        this.CWave2Triangle = wave2Triangle;
        this.CWave2Square = wave2Square;
        this.CWave3Triangle = wave3Triangle;
        this.CWave3Square = wave3Square;
        startWave.setCycleCount(10);
        past.setCycleCount(1);
        wave = 1;
        timer.setCycleCount(Animation.INDEFINITE);
        timingWave.setCycleCount(Animation.INDEFINITE);
    }
    public void createSquare(){
        new SquareModel(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
        nowS--;
    }
    public void createTriangle(){
        new TriangleModel(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
        nowT--;
    }
    public void createArchmire(){
        new Archmire(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
    }
    public void createOmenoct(){
        new Omenoct(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
    }
    public void createNecropick(){
        new NecroPick(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
    }
    public void createWyrm(){
        new Wyrm(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
    }
    public void createBarricados(){
        new Barricados(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
    }
    public void createBlackOrb(){
        Thread thread = new Thread(){
            long time = System.currentTimeMillis();
            boolean c = false;
            @Override
            public void run() {
                if(!c){
                    c = true;
                    new IsoNonRigidScene(SCREEN_WIDTH/2 - 10, SCREEN_WIDTH/2 + 50,
                            SCREEN_HEIGHT/4 - 10, SCREEN_HEIGHT/4 + 50);
                    new IsoNonRigidScene(SCREEN_WIDTH/5 - 10, SCREEN_WIDTH/5 + 50,
                            SCREEN_HEIGHT*2/4 - 10, SCREEN_HEIGHT*2/4 + 50);
                    new IsoNonRigidScene(SCREEN_WIDTH*4/5 - 10, SCREEN_WIDTH*4/5 + 50,
                            SCREEN_HEIGHT*2/4 - 10, SCREEN_HEIGHT*2/4 + 50);
                    new IsoNonRigidScene(SCREEN_WIDTH*2/5 - 10, SCREEN_WIDTH*2/5 + 50,
                            SCREEN_HEIGHT*3/4 - 10, SCREEN_HEIGHT*3/4 + 50);
                    new IsoNonRigidScene(SCREEN_WIDTH*3/5 - 10, SCREEN_WIDTH*3/5 + 50,
                            SCREEN_HEIGHT*3/4 - 10, SCREEN_HEIGHT*3/4 + 50);
                }
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                new BlackOrb(SCREEN_WIDTH/2, SCREEN_HEIGHT/4);
                new BlackOrb(SCREEN_WIDTH/5, SCREEN_HEIGHT*2/4);
                new BlackOrb(SCREEN_WIDTH*4/5, SCREEN_HEIGHT*2/4);
                new BlackOrb(SCREEN_WIDTH*2/5, SCREEN_HEIGHT*3/4);
                new BlackOrb(SCREEN_WIDTH*3/5, SCREEN_HEIGHT*3/4);
            }
        };
        thread.start();
    }
    public void stop(){
        startWave.stop();
    }
    private int nowT, nowS, time = 0;
    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
        totalTime++;
        switch (wave){
            case 1:
                w1Time++;
                break;
            case 2:
                w2Time++;
                break;
            case 3:
                w3Time++;
                break;
            case 5:
                w5Time++;
                break;
            case 6:
                w6Time++;
                break;
            case 7:
                w7Time++;
                break;
            case 8:
                w8Time++;
                break;
            case 9:
                w9Time++;
                break;
            case 11:
                w11Time++;
                break;
        }
    }));
    private int enemyNumber = 0, enemyDead = 0;
    private boolean isArchmire = false, isWyrm = false, isNecropick = false, isOmenoct = false,
            isBlackOrb = false, isBarricados = false;

    public synchronized void enemyDead(){
        enemyDead++;
        if(enemyNumber == enemyDead){
            enoughEnemy = true;
        }
    }

    private boolean enoughEnemy = false;

    Timeline timingWave = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> {
        int waveTime = switch (wave) {
            case 5 -> w5Time;
            case 6 -> w6Time;
            case 7 -> w7Time;
            case 8 -> w8Time;
            case 9 -> w9Time;
            default -> 0;
        };
        int enemies = 0;
        if(enoughEnemy) {
            finishWave();
            return;
        }
        if(isArchmire)
            for(int i = 0; i < waveTime/6 ; i++){
                if(Math.random()*100 < 50) {
                    createArchmire();
                    enemies++;
                }
            }
        if(isOmenoct)
            for(int i = 0; i < waveTime/5; i++){
                if(Math.random()*100 < 50) {
                    createOmenoct();
                    enemies++;
                }
            }
        if(isWyrm)
            for(int i = 0; i < waveTime/4; i++){
                if(Math.random()*100 < 50) {
                    createWyrm();
                    enemies++;
                }
            }
        if(isNecropick)
            for(int i = 0; i < waveTime/8; i++){
                if(Math.random()*100 < 50) {
                    createNecropick();
                    enemies++;
                }
            }
        if(isBarricados)
            for(int i = 0; i < waveTime/10; i++){
                if(Math.random()*100 < 60) {
                    createBarricados();
                    enemies++;
                }
            }
        if(isBlackOrb)
            for(int i = 0; i < 15; i++){
                if(Math.random()*100 < 100) {
                    createBlackOrb();
                    enemies++;
                }
            }
        if(enemies < waveTime/5)
            for(int i = 0; i < waveTime/3 - enemies; i += 2){
                createSquare();
                createTriangle();
            }
    }));
    Timeline startWave = new Timeline(new KeyFrame(Duration.seconds(6), actionEvent -> {
        if(10 - time > nowT){
            if(Math.random() * 100 < 50)
                createTriangle();
        }
        else{
            createTriangle();
        }
        if(10 - time > nowS){
            if(Math.random() * 100 < 50)
                createSquare();
        }
        else{
            createSquare();
        }
        time++;
        if(time == 10){
            time = 0;
            finishWave();
        }
    }));
    Timeline past = new Timeline(new KeyFrame(Duration.seconds(4), actionEvent -> {
        startWave.play();
        timer.play();
    }));
    public void finishWave(){
        if(!(BlackOrb.blackOrbs.isEmpty() && Barricados.barricados.isEmpty() && TriangleModel.triangleModels.isEmpty() &&
            SquareModel.squareModels.isEmpty() && Wyrm.wyrms.isEmpty() && Archmire.archmires.isEmpty() && NecroPick.necroPicks.isEmpty() &&
            Omenoct.omenocts.isEmpty()))
            return;
        waveFinish = true;
        timer.pause();
        wave++;
    }
    public void playWave1(){
        nowS = wave1Square;
        nowT = wave1Triangle;
        waveFinish = false;

        past.play();
    }
    public void playWave2(){
        nowS = wave2Square;
        nowT = wave2Triangle;
        waveFinish = false;

        past.play();
    }
    public void playWave3(){
        nowS = wave3Square;
        nowT = wave3Triangle;
        waveFinish = false;

        past.play();
    }
    public void playWave5(){
        waveFinish = false;

        enoughEnemy = false;
        enemyNumber = (int) (4 + (difficulty)/100 * 6);
        isBlackOrb = false;
        isBarricados = false;
        isArchmire = false;
        isNecropick = false;
        isWyrm = false;
        isOmenoct = true;

        past.play();
    }
    public void playWave6(){
        waveFinish = false;

        enoughEnemy = false;
        enemyNumber = (int) (5 + (difficulty)/100 * 7);
        isBlackOrb = false;
        isBarricados = false;
        isArchmire = false;
        isNecropick = true;
        isWyrm = true;
        isOmenoct = true;

        past.play();
    }
    public void playWave7(){
        waveFinish = false;

        enoughEnemy = false;
        enemyNumber = (int) (6 + (difficulty)/100 * 6);
        isBlackOrb = false;
        isBarricados = false;
        isArchmire = true;
        isNecropick = true;
        isWyrm = true;
        isOmenoct = true;

        past.play();
    }
    public void playWave8(){
        waveFinish = false;

        enoughEnemy = false;
        enemyNumber = (int) (7 + (difficulty)/100 * 7);
        isBlackOrb = false;
        isBarricados = true;
        isArchmire = true;
        isNecropick = true;
        isWyrm = true;
        isOmenoct = true;

        past.play();
    }
    public void playWave9(){
        waveFinish = false;

        enoughEnemy = false;
        enemyNumber = (int) (8 + (difficulty)/100 * 8);
        isBlackOrb = true;
        isBarricados = true;
        isArchmire = true;
        isNecropick = true;
        isWyrm = true;
        isOmenoct = true;

        past.play();
    }
    public void playWave11(){
        waveFinish = false;

        past.play();
    }
}
