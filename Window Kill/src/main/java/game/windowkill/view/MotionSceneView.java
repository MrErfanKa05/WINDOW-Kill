package game.windowkill.view;

import game.windowkill.controller.Constant;
import game.windowkill.controller.DataHolder;
import game.windowkill.controller.InputManager;
import game.windowkill.controller.builder.EnemyBuilder;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.view.characterView.EpsilonView;
import game.windowkill.view.characterView.enemy.SquareView;
import game.windowkill.view.characterView.enemy.TriangleView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    public void paint(){
        pane.getChildren().clear();
        setPage();

        addBars();

        File file = new File("src/main/resources/game/windowkill/resources/circle.png");
        ImageView imageView = null;
        try {
            imageView = new ImageView(String.valueOf(file.toURI().toURL()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        imageView.setFitWidth(EPSILON_DIAMETER);
        imageView.setFitHeight(EPSILON_DIAMETER);
        imageView.setTranslateX(EpsilonView.getInstance().getX());
        imageView.setTranslateY(EpsilonView.getInstance().getY());

        pane.getChildren().add(imageView);

        for(BulletView bullet : BulletView.bulletViews){
//            if(b)
            Circle bull = new Circle();
            bull.setFill(Color.DARKMAGENTA);
            bull.setRadius(Constant.BULLET_RADIUS);
            bull.setTranslateX(bullet.getPosition().getX());
            bull.setTranslateY(bullet.getPosition().getY());
            pane.getChildren().add(bull);
        }

        for(SquareView squareView : SquareView.squareViews){
            if(squareView.getPosition().getX() + SQUARE_OUTSIDE + stage.getX() < left ||
                squareView.getPosition().getX() + stage.getX() > right ||
                squareView.getPosition().getY() + SQUARE_OUTSIDE + stage.getY() < top ||
                squareView.getPosition().getY() + stage.getY() > down)
                continue;
            Rectangle rectangleOut = new Rectangle();
            rectangleOut.setFill(Color.GREEN);
            rectangleOut.setHeight(SQUARE_OUTSIDE);
            rectangleOut.setWidth(SQUARE_OUTSIDE);
            rectangleOut.setX(squareView.getPosition().getX());
            rectangleOut.setY(squareView.getPosition().getY());

            Rectangle rectangleIn = new Rectangle();
            rectangleIn.setFill(Color.BLACK);
            rectangleIn.setHeight(SQUARE_INSIDE);
            rectangleIn.setWidth(SQUARE_INSIDE);
            rectangleIn.setX(squareView.getPosition().getX() + 4);
            rectangleIn.setY(squareView.getPosition().getY() + 4);

            pane.getChildren().add(rectangleOut);
            pane.getChildren().add(rectangleIn);
        }

        for(CollectableItemView collectableItemView : CollectableItemView.collectableItemViews){
            Circle circle = new Circle();
            circle.setRadius(collectableItemView.getRadius());
//            circle.setRadius(20);
            circle.setTranslateX(collectableItemView.getPosition().getX());
            circle.setTranslateY(collectableItemView.getPosition().getY());
            circle.setFill(collectableItemView.getColor());

            pane.getChildren().add(circle);
        }

        for(TriangleView triangleView : TriangleView.triangleViews){
            if(triangleView.getPosition().getX() + TRIANGLE_OUTSIDE + stage.getX() < left ||
                    triangleView.getPosition().getX() + stage.getX() > right ||
                    triangleView.getPosition().getY() + TRIANGLE_OUTSIDE + stage.getY() < top ||
                    triangleView.getPosition().getY() + stage.getY() > down)
                continue;
            //todo
            file = new File(TRIANGLE_PATH);

            ImageView triangleImage = null;
            try {
                triangleImage = new ImageView(String.valueOf(file.toURI().toURL()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            triangleImage.setTranslateX(triangleView.getPosition().getX());
            triangleImage.setTranslateY(triangleView.getPosition().getY());
            triangleImage.setFitHeight(TRIANGLE_OUTSIDE);
            triangleImage.setFitWidth(TRIANGLE_OUTSIDE);

            pane.getChildren().add(triangleImage);
        }
    }
}
