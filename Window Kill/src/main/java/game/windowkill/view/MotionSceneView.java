package game.windowkill.view;

import game.windowkill.controller.Constant;
import game.windowkill.controller.DataHolder;
import game.windowkill.controller.InputManager;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.view.characterView.EpsilonView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static game.windowkill.controller.Constant.INSIDE_EPSILON_COLOR;
import static game.windowkill.controller.Constant.OUTSIDE_EPSILON_COLOR;

public class MotionSceneView {
    private static MotionSceneView model;
    private Stage stage;
    private Scene scene;
    private Pane pane;
    public InputManager inputManager;

    public static MotionSceneView getInstance() {
        if(model == null)model = new MotionSceneView(DataHolder.stage);
        return model;
    }
    private MotionSceneView(Stage stage){
        this.stage = stage;
    }
    public void init(){
        pane = new Pane();
        scene = new Scene(pane, 500, 500);
        inputManager = new InputManager(scene);
//        scene.setOnMouseClicked(mouseEvent -> setScene());
        stage.setScene(scene);
    }
//    public void setScene(){
//        System.out.println("dasdadsa");
//    }
    public void set(double top, double down, double right, double left){
        top -= 10;
        stage.setX(left);
        stage.setWidth(right - left);
        stage.setY(top);
        stage.setHeight(down - top);
    }
    public void addBars(){

    }
    public void paint(){
        pane.getChildren().clear();
        addBars();

        Circle circle = new Circle();
        circle.setRadius(EpsilonView.getInstance().getRadius());
        circle.setTranslateX(EpsilonView.getInstance().getX());
        circle.setTranslateY(EpsilonView.getInstance().getY());
        circle.setFill(OUTSIDE_EPSILON_COLOR);

        Circle inCircle = new Circle();
        inCircle.setRadius(EpsilonView.getInstance().getRadius() - 4);
        inCircle.setTranslateX(EpsilonView.getInstance().getX() + 0);
        inCircle.setTranslateY(EpsilonView.getInstance().getY() + 0);
        inCircle.setFill(INSIDE_EPSILON_COLOR);


        pane.getChildren().add(circle);
        pane.getChildren().add(inCircle);

        for(BulletView bullet : BulletView.bulletViews){
            Circle bull = new Circle();
            circle.setFill(Color.DARKMAGENTA);
            circle.setRadius(Constant.BULLET_RADIUS);
            circle.setTranslateX(bullet.getPosition().getX());
            circle.setTranslateY(bullet.getPosition().getY());
//            circle.setFill(Color.AZURE);

            pane.getChildren().add(bull);
        }
    }
}
