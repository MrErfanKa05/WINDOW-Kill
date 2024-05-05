package game.windowkill.view;

import game.windowkill.controller.Constant;
import game.windowkill.controller.DataHolder;
import game.windowkill.controller.InputManager;
import game.windowkill.view.characterView.EpsilonView;
import game.windowkill.view.characterView.SquareView;
import game.windowkill.view.characterView.TriangleView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

import static game.windowkill.controller.Constant.*;

public class MotionSceneView {
    private static MotionSceneView model;
    private Stage stage;
    private Scene scene;
    private Pane pane;
    public InputManager inputManager;
    private Label Wave, ElapsedTime, XP, HP;
    private int elapsedTimeInt = 0;
    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
        elapsedTimeInt++;
        ElapsedTime.setText(String.valueOf(elapsedTimeInt));
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

        Wave = new Label("0");
        Wave.setTranslateX(5);
        Wave.setTranslateY(5);
        Wave.setFont(new Font("Bold", 15));
        Wave.setTextFill(Color.WHITE);

        ElapsedTime = new Label("0");
        ElapsedTime.setTranslateX(30);
        ElapsedTime.setTranslateY(5);
        ElapsedTime.setFont(new Font("Bold", 15));
        ElapsedTime.setTextFill(Color.FIREBRICK);

        XP = new Label("0");
        XP.setTranslateX(140);
        XP.setTranslateY(5);
        XP.setFont(new Font("Bold", 15));
        XP.setTextFill(Color.YELLOW);

        HP = new Label("100");
        HP.setTranslateX(100);
        HP.setTranslateY(5);
        HP.setFont(new Font("Bold", 15));
        HP.setTextFill(Color.RED);

        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }
//    public void setScene(){
//        System.out.println("dasdadsa");
//    }
    private double top, down, left, right;
    public void set(double top, double down, double right, double left){
//        top -= 10;
        this.top = top;
        this.left = left;
        this.right = right;
        this.down = down;
        stage.setX(left);
        stage.setWidth(right - left);
        stage.setY(top);
        stage.setHeight(down - top);
    }
    public void addBars(){
        pane.getChildren().add(Wave);
        pane.getChildren().add(HP);
        pane.getChildren().add(XP);
        pane.getChildren().add(ElapsedTime);
    }
    public void paint(){
        pane.getChildren().clear();
        addBars();

        Circle circleOut = new Circle();
        circleOut.setRadius(EpsilonView.getInstance().getRadius());
        circleOut.setTranslateX(EpsilonView.getInstance().getX());
        circleOut.setTranslateY(EpsilonView.getInstance().getY());
        circleOut.setFill(OUTSIDE_EPSILON_COLOR);

        Circle inCircle = new Circle();
        inCircle.setRadius(EpsilonView.getInstance().getRadius() - 4);
        inCircle.setTranslateX(EpsilonView.getInstance().getX() + 0);
        inCircle.setTranslateY(EpsilonView.getInstance().getY() + 0);
        inCircle.setFill(INSIDE_EPSILON_COLOR);


        pane.getChildren().add(circleOut);
        pane.getChildren().add(inCircle);

        for(BulletView bullet : BulletView.bulletViews){
//            if(b)
            Circle bull = new Circle();
            bull.setFill(Color.DARKMAGENTA);
            bull.setRadius(Constant.BULLET_RADIUS);
            bull.setTranslateX(bullet.getPosition().getX());
            bull.setTranslateY(bullet.getPosition().getY());
//            circle.setFill(Color.AZURE);

            pane.getChildren().add(bull);
        }

        for(SquareView squareView : SquareView.squareViews){
            if(squareView.getPosition().getX() + 20 < left ||
                squareView.getPosition().getX() > right ||
                squareView.getPosition().getY() + 20 < top ||
                squareView.getPosition().getY() > down)
                continue;
            Rectangle rectangleOut = new Rectangle();
            rectangleOut.setFill(Color.GREEN);
            rectangleOut.setHeight(20);
            rectangleOut.setWidth(20);
            rectangleOut.setX(squareView.getPosition().getX());
            rectangleOut.setY(squareView.getPosition().getY());

            Rectangle rectangleIn = new Rectangle();
            rectangleIn.setFill(Color.BLACK);
            rectangleIn.setHeight(16);
            rectangleIn.setWidth(16);
            rectangleIn.setX(squareView.getPosition().getX() + 2);
            rectangleIn.setY(squareView.getPosition().getY() + 2);

            pane.getChildren().add(rectangleOut);
            pane.getChildren().add(rectangleIn);
        }

        for(TriangleView triangleView : TriangleView.triangleViews){
            if(triangleView.getPosition().getX() + 20 < left ||
                    triangleView.getPosition().getX() > right ||
                    triangleView.getPosition().getY() + 20 < top ||
                    triangleView.getPosition().getY() > down)
                continue;
            //TODO : make it correct;
            Point2D left = new Point2D(triangleView.getPosition().getX(), triangleView.getPosition().getY() + 20);
            Point2D right = new Point2D(triangleView.getPosition().getX() + 20, triangleView.getPosition().getY() + 20);
            Point2D up = new Point2D(triangleView.getPosition().getX() + 10, triangleView.getPosition().getY());

            Line leftLine = new Line();
            leftLine.setFill(Color.YELLOW);
            leftLine.setStartX(left.getX());
            leftLine.setStartY(left.getY());
            leftLine.setEndX(up.getX());
            leftLine.setEndY(up.getY());

            Line rightLine = new Line();
            rightLine.setFill(Color.YELLOW);
            rightLine.setStartX(right.getX());
            rightLine.setStartY(right.getY());
            rightLine.setEndX(up.getX());
            rightLine.setEndY(up.getY());

            Line downLine = new Line();
//            downLine.setTranslateX(right.getX());
//            downLine.setTranslateY(right.getY());
            downLine.setFill(Paint.valueOf("0xffff00ff"));
            downLine.setStartX(right.getX());
            downLine.setStartY(right.getY());
            downLine.setEndX(left.getX());
            downLine.setEndY(left.getY());
            System.out.println(downLine.getTranslateX() + " " + downLine.getTranslateY());

            pane.getChildren().add(downLine);
            pane.getChildren().add(leftLine);
            pane.getChildren().add(rightLine);
        }
    }
}
