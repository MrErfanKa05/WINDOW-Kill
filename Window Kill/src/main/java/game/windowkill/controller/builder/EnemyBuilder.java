package game.windowkill.controller.builder;

import game.windowkill.model.characterModel.SquareModel;
import game.windowkill.model.characterModel.TriangleModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.imageio.plugins.tiff.TIFFDirectory;
import java.util.Random;

import static game.windowkill.controller.Constant.MAX_WIDTH;
import static game.windowkill.controller.DataHolder.difficulty;

public class EnemyBuilder {
    private int wave1Triangle, wave1Square, wave2Triangle, wave2Square, wave3Triangle, wave3Square;
    private int CWave1Triangle, CWave1Square, CWave2Triangle, CWave2Square, CWave3Triangle, CWave3Square;
    private Random random = new Random();
    public int wave = 1;
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
    }
    private int nowT, nowS;
    Timeline startWave = new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> {

    }));
    public void playWave1(){
        nowS = wave1Square;
        nowT = wave1Triangle;
        waveFinish = false;
        for(int i = 0; i < wave1Square; i++) {
            int x, y;
            x = random.nextInt((int) MAX_WIDTH);
            y = random.nextInt((int) MAX_WIDTH);
            new SquareModel(x, y);
        }
        for(int i= 0; i < wave1Triangle; i++){
            int x, y;
            x = random.nextInt((int) MAX_WIDTH);
            y = random.nextInt((int) MAX_WIDTH);
            new TriangleModel(x, y);
        }
        wave++;
    }
    public void playWave2(){
        nowS = wave2Square;
        nowT = wave2Triangle;
        waveFinish = false;
        for(int i = 0; i < wave2Square; i++) {
            int x, y;
            x = random.nextInt((int) MAX_WIDTH);
            y = random.nextInt((int) MAX_WIDTH);
            new SquareModel(x, y);
        }
        for(int i= 0; i < wave2Triangle; i++){
            int x, y;
            x = random.nextInt((int) MAX_WIDTH);
            y = random.nextInt((int) MAX_WIDTH);
            new TriangleModel(x, y);
        }
        wave++;
    }
    public void playWave3(){
        nowS = wave3Square;
        nowT = wave3Triangle;
        waveFinish = false;
        for(int i = 0; i < wave3Square; i++) {
            int x, y;
            x = random.nextInt((int) MAX_WIDTH);
            y = random.nextInt((int) MAX_WIDTH);
            new SquareModel(x, y);
        }
        for(int i= 0; i < wave3Triangle; i++){
            int x, y;
            x = random.nextInt((int) MAX_WIDTH);
            y = random.nextInt((int) MAX_WIDTH);
            new TriangleModel(x, y);
        }
        wave++;
    }
}
