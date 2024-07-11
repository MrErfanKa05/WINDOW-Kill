package game.windowkill.controller.builder;

import game.windowkill.model.characterModel.enemy.SquareModel;
import game.windowkill.model.characterModel.enemy.TriangleModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

import static game.windowkill.controller.Constant.*;
import static game.windowkill.controller.DataHolder.difficulty;

public class EnemyBuilder {
    private int wave1Triangle, wave1Square, wave2Triangle, wave2Square, wave3Triangle, wave3Square;
    private int CWave1Triangle, CWave1Square, CWave2Triangle, CWave2Square, CWave3Triangle, CWave3Square;
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
    }
    public void createSquare(){
        new SquareModel(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
        nowS--;
    }
    public void createTriangle(){
        new TriangleModel(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
        nowT--;
    }
    public void stop(){
        startWave.stop();
    }
    private int nowT, nowS, time = 0;
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
            waveFinish = true;
            wave++;
        }
    }));
    Timeline past = new Timeline(new KeyFrame(Duration.seconds(4), actionEvent -> {
        startWave.play();
    }));
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
}
