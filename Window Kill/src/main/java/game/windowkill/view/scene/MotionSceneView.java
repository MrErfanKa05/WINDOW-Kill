package game.windowkill.view.scene;

import game.windowkill.controller.Constant;
import game.windowkill.controller.DataHolder;
import game.windowkill.controller.InputManager;
import game.windowkill.controller.builder.EnemyBuilder;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.enemy.Archmire;
import game.windowkill.view.CollectableItemView;
import game.windowkill.view.bullet.EnemyBulletView;
import game.windowkill.view.bullet.EpsilonBulletView;
import game.windowkill.view.characterView.EntityView;
import game.windowkill.view.characterView.EpsilonView;
import game.windowkill.view.characterView.enemy.*;
import game.windowkill.view.characterView.miniBoss.BarricadosView;
import game.windowkill.view.characterView.miniBoss.BlackOrbView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;

import static game.windowkill.controller.Constant.*;

public class MotionSceneView {
    private static MotionSceneView model;
    private Stage stage;
    private Scene scene;
    public Pane pane;
    public InputManager inputManager;
    private Label Wave, ElapsedTime, XP, HP;
    private int elapsedTimeInt = 0;
    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
        elapsedTimeInt++;
        ElapsedTime.setText("time:" + String.valueOf(elapsedTimeInt));
    }));

    public InputManager getInputManager() {
        return inputManager;
    }

    public static MotionSceneView getInstance() {
        if(model == null)model = new MotionSceneView(DataHolder.stage);
        return model;
    }
    private MotionSceneView(Stage stage){
        this.stage = stage;
    }
    public void init(){
        pane = new Pane();
        scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
        inputManager = new InputManager(scene);
//        scene.setOnMouseClicked(mouseEvent -> setScene());
        stage.setScene(scene);
        pane.setBackground(Background.fill(Color.BLACK));

        Wave = new Label("wave:0");
        Wave.setTranslateX(5);
        Wave.setTranslateY(5);
        Wave.setFont(new Font("Bold", 15));
        Wave.setTextFill(Color.WHITE);

        ElapsedTime = new Label("time:0");
        ElapsedTime.setTranslateX(30);
        ElapsedTime.setTranslateY(5);
        ElapsedTime.setFont(new Font("Bold", 15));
        ElapsedTime.setTextFill(Color.FIREBRICK);

        XP = new Label("0");
        XP.setTranslateX(150);
        XP.setTranslateY(5);
        XP.setFont(new Font("Bold", 15));
        XP.setTextFill(Color.YELLOW);

        HP = new Label("100");
        HP.setTranslateX(100);
        HP.setTranslateY(5);
        HP.setFont(new Font("Bold", 15));
        HP.setTextFill(Color.RED);

        addBars();

        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    public int getElapsedTimeInt() {
        return elapsedTimeInt;
    }

    //    public void setScene(){
//        System.out.println("dasdadsa");
//    }
    private double top, down, left, right;

    public double getTop() {
        return top;
    }

    public double getDown() {
        return down;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public void set(double top, double down, double right, double left){
//        top -= 10;
        this.top = top;
        this.left = left;
        this.right = right;
        this.down = down;
    }
    public void addBars(){
        Wave.setText("⚡:" + String.valueOf(EnemyBuilder.wave));
        pane.getChildren().add(Wave);

        HP.setText("❤:" + String.valueOf((int)(EpsilonModel.getInstance().getHp())));
        pane.getChildren().add(HP);

        XP.setText("⚔:" + String.valueOf((int) EpsilonModel.getInstance().getXP()));
        pane.getChildren().add(XP);

        pane.getChildren().add(ElapsedTime);
    }
    public void setPage(){
        stage.setX(left);
        stage.setY(top);
        stage.setWidth(right - left);
        stage.setHeight(down - top);
    }
    public void imagePaint(File file, int size, EntityView view){
        ImageView imageView = null;
        try {
            imageView = new ImageView(String.valueOf(file.toURI().toURL()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        imageView.setTranslateX(view.getPosition().getX() - stage.getX());
        imageView.setTranslateY(view.getPosition().getY() - stage.getY());
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);

        pane.getChildren().add(imageView);
    }

    public void paint(){
        pane.getChildren().clear();
        setPage();

        addBars();

        for(Point2D view : ArchmireView.areas){
            if(view.getX() < left ||
                    view.getX() > right ||
                    view.getY() < top ||
                    view.getY() > down)
                continue;
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.LIGHTPINK);
            rectangle.setTranslateX(view.getX() - stage.getX());
            rectangle.setTranslateY(view.getY() - stage.getY());
            rectangle.setHeight(BIG_ENEMY_OUTSIDE);
            rectangle.setWidth(BIG_ENEMY_OUTSIDE);
            pane.getChildren().add(rectangle);

        }

        for(ArchmireView view : ArchmireView.archmireViews){
            if(view.getPosition().getX() + BIG_ENEMY_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + BIG_ENEMY_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(ARCHMIRE_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(NecropickView view : NecropickView.necropickViews){
            if(view.getPosition().getX() + BIG_ENEMY_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + BIG_ENEMY_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            if(!view.onGround)
                continue;

            File file = new File(NECROPICK_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(OmenoctView view : OmenoctView.omenoctViews){
            if(view.getPosition().getX() + BIG_ENEMY_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + BIG_ENEMY_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(OMENOCT_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(WyrmView view : WyrmView.wyrmViews){
            if(view.getPosition().getX() + BIG_ENEMY_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + BIG_ENEMY_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(WYRM_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(BarricadosView view : BarricadosView.barricadosViews){
            if(view.getPosition().getX() + BARRICADOS_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + BARRICADOS_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(BARRICADOS_PATH);

            imagePaint(file, BARRICADOS_OUTSIDE, view);
        }

        for(BlackOrbView view : BlackOrbView.blackOrbViews){
            if(view.getPosition().getX() + BIG_ENEMY_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + BIG_ENEMY_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(BLACKORB_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        {
            EpsilonView view = EpsilonView.getInstance();
            if (!(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)) {
                File file = new File("src/main/resources/game/windowkill/resources/circle.png");
                ImageView imageView = null;
                try {
                    imageView = new ImageView(String.valueOf(file.toURI().toURL()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                imageView.setFitWidth(EpsilonView.getInstance().getRadius() * 2);
                imageView.setFitHeight(EpsilonView.getInstance().getRadius() * 2);
                imageView.setTranslateX(EpsilonView.getInstance().getX() - EpsilonView.getInstance().getRadius() - stage.getX());
                imageView.setTranslateY(EpsilonView.getInstance().getY() - EpsilonView.getInstance().getRadius() - stage.getY());

                pane.getChildren().add(imageView);
            }
        }

        //todo : add cerberusbullet

        for(EnemyBulletView view : EnemyBulletView.enemyBulletViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;
            Circle bull = new Circle();
            bull.setFill(Color.HOTPINK);
            bull.setRadius(Constant.BULLET_RADIUS);
            bull.setTranslateX(view.getPosition().getX() - BULLET_RADIUS - stage.getX());
            bull.setTranslateY(view.getPosition().getY() - BULLET_RADIUS - stage.getY());
            pane.getChildren().add(bull);
        }

        for(EpsilonBulletView view : EpsilonBulletView.epsilonBulletViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;
//            if(b)
            Circle bull = new Circle();
            bull.setFill(Color.DARKMAGENTA);
            bull.setRadius(Constant.BULLET_RADIUS);
            bull.setTranslateX(view.getPosition().getX() - BULLET_RADIUS - stage.getX());
            bull.setTranslateY(view.getPosition().getY() - BULLET_RADIUS - stage.getY());
            pane.getChildren().add(bull);
        }

        for(SquareView squareView : SquareView.squareViews){
            if(squareView.getPosition().getX() + SQUARE_OUTSIDE < left ||
                    squareView.getPosition().getX() > right ||
                    squareView.getPosition().getY() + SQUARE_OUTSIDE < top ||
                    squareView.getPosition().getY() > down)
                continue;
            Rectangle rectangleOut = new Rectangle();
            rectangleOut.setFill(Color.GREEN);
            rectangleOut.setHeight(SQUARE_OUTSIDE);
            rectangleOut.setWidth(SQUARE_OUTSIDE);
            rectangleOut.setTranslateX(squareView.getPosition().getX() - stage.getX());
            rectangleOut.setTranslateY(squareView.getPosition().getY() - stage.getY());

            Rectangle rectangleIn = new Rectangle();
            rectangleIn.setFill(Color.BLACK);
            rectangleIn.setHeight(SQUARE_INSIDE);
            rectangleIn.setWidth(SQUARE_INSIDE);
            rectangleIn.setTranslateX(squareView.getPosition().getX() + 4 - stage.getX());
            rectangleIn.setTranslateY(squareView.getPosition().getY() + 4 - stage.getY());

            pane.getChildren().add(rectangleOut);
            pane.getChildren().add(rectangleIn);
        }

        for(CollectableItemView view : CollectableItemView.collectableItemViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;
            Circle circle = new Circle();
            circle.setRadius(view.getRadius());
//            circle.setRadius(20);
            circle.setTranslateX(view.getPosition().getX() - stage.getX());
            circle.setTranslateY(view.getPosition().getY() - stage.getY());
            circle.setFill(view.getColor());

            pane.getChildren().add(circle);
        }

        for(TriangleView view : TriangleView.triangleViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(TRIANGLE_PATH);

            imagePaint(file, (int) TRIANGLE_OUTSIDE, view);
        }
    }
}
