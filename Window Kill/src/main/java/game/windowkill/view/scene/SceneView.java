package game.windowkill.view.scene;

import game.windowkill.controller.Constant;
import game.windowkill.model.characterModel.enemy.Archmire;
import game.windowkill.model.characterModel.enemy.Omenoct;
import game.windowkill.model.characterModel.enemy.TriangleModel;
import game.windowkill.model.characterModel.enemy.Wyrm;
import game.windowkill.view.CollectableItemView;
import game.windowkill.view.bullet.EnemyBulletView;
import game.windowkill.view.bullet.EpsilonBulletView;
import game.windowkill.view.characterView.EntityView;
import game.windowkill.view.characterView.EpsilonView;
import game.windowkill.view.characterView.enemy.*;
import game.windowkill.view.characterView.miniBoss.BarricadosView;
import game.windowkill.view.characterView.miniBoss.BlackOrbView;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.MalformedURLException;

import static game.windowkill.controller.Constant.*;
import static game.windowkill.controller.Constant.TRIANGLE_OUTSIDE;
import static game.windowkill.controller.DataHolder.stage;

public abstract class SceneView {
    protected String id;
    protected Point2D position;
    protected double left, right, top, down;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SceneView(String id, double left, double right, double top, double down){
        this.id = id;
        set(left, right, top, down);
        stage = new Stage();
        pane = new Pane();
        scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        init();
    }

    public void init(){
        pane.setBackground(Background.fill(Color.BLACK));

        stage.setX(getLeft());
        stage.setY(getTop());
        stage.setWidth(right - left);
        stage.setHeight(down - top);

        stage.setResizable(false);
        stage.setTitle("");
        stage.setIconified(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(false);
        stage.show();
    }

    private Pane pane;
    private Scene scene;
    private Stage stage;
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

        for(ArchmireView view : ArchmireView.archmireViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(ARCHMIRE_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(NecropickView view : NecropickView.necropickViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(NECROPICK_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(OmenoctView view : OmenoctView.omenoctViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(OMENOCT_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(WyrmView view : WyrmView.wyrmViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(WYRM_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(BarricadosView view : BarricadosView.barricadosViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
                    view.getPosition().getY() > down)
                continue;

            File file = new File(BARRICADOS_PATH);

            imagePaint(file, BIG_ENEMY_OUTSIDE, view);
        }

        for(BlackOrbView view : BlackOrbView.blackOrbViews){
            if(view.getPosition().getX() + TRIANGLE_OUTSIDE < left ||
                    view.getPosition().getX() > right ||
                    view.getPosition().getY() + TRIANGLE_OUTSIDE < top ||
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
            bull.setFill(Color.GREENYELLOW);
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

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }

    public void set(double left, double right, double top, double down){
        this.left = left;
        this.right = right;
        this.top = top;
        this.down = down;
    }

    public javafx.geometry.Point2D getPosition() {
        double paneX = position.getX();
        double paneY = position.getY();
//        System.out.println(position.getX() + " " + paneX);
        return new Point2D(paneX, paneY);
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
