package game.windowkill.controller;

import game.windowkill.view.MotionSceneView;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class InputManager{

//    private static InputManager inputManager;
//
//    public static InputManager getInstance() {
//        if(inputManager == null) inputManager = new InputManager(MotionSceneView.getInstance());
//        return inputManager;
//    }
    private EventHandler<? super NewMouseEvent> var;

    public static class NewMouseEvent extends MouseEvent{
        private Point2D mousePosition;
        public NewMouseEvent(Point2D mousePosition){
            super(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,
                    null, 0, false, false, false, false,
                    false, false, false, false, false, false, null);
            this.mousePosition = mousePosition;
        }
        public void setPosition(Point2D position){
            this.mousePosition = position;
        }
        public Point2D getMousePosition() {
            return mousePosition;
        }
    }

    private Scene scene;

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean shop = false;
    private boolean ability = false;

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isShop() {
        return shop;
    }

    public boolean isAbility() {
        return ability;
    }

    private String upString = "w";
    private String downString = "s";
    private String leftString = "a";
    private String rightString = "d";
    private String shopString = "c";
    private String abilityString = "e";

    private Point2D mousePosition = new Point2D(0, 0);
    private NewMouseEvent mouseEvent = new NewMouseEvent(mousePosition);

    public InputManager(Scene scene){
        this.scene = scene;
        update();
    }
    public void update(){
        scene.setOnMouseClicked(mouseEvent -> mouseClicked(mouseEvent));
        scene.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));
        scene.setOnMouseReleased(mouseEvent -> mouseReleased(mouseEvent));
        scene.setOnMouseMoved(mouseEvent -> mouseMoved(mouseEvent));

        scene.setOnKeyReleased(keyEvent -> keyReleased(keyEvent));
        scene.setOnKeyPressed(keyEvent -> keyPressed(keyEvent));
        scene.setOnKeyTyped(keyEvent -> keyTyped(keyEvent));
    }
    private void keyTyped(KeyEvent keyEvent){
        this.keyPressed(keyEvent);
    }
    private void keyPressed(KeyEvent keyEvent){
        if(keyEvent.getText().equals(downString))
            down = true;
        if(keyEvent.getText().equals(upString))
            up = true;
        if(keyEvent.getText().equals(leftString))
            left = true;
        if(keyEvent.getText().equals(rightString))
            right = true;
        if(keyEvent.getText().equals(shopString))
            shop = true;
        if(keyEvent.getText().equals(abilityString))
            ability = true;
    }
    private void keyReleased(KeyEvent keyEvent){
        if(keyEvent.getText().equals(downString))
            down = false;
        if(keyEvent.getText().equals(upString))
            up = false;
        if(keyEvent.getText().equals(leftString))
            left = false;
        if(keyEvent.getText().equals(rightString))
            right = false;
        if(keyEvent.getText().equals(shopString))
            shop = false;
        if(keyEvent.getText().equals(abilityString))
            ability = false;
    }

    private void mouseMoved(MouseEvent mouseEvent){
        mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        this.mouseEvent.setPosition(mousePosition);
//        System.out.println(mousePosition);
    }
    public void setOnLeftClick(EventHandler<? super NewMouseEvent> var1){
        this.var = var1;
    }
    private void mousePressed(MouseEvent mouseEvent){
        mouseClicked(mouseEvent);
        //maybe noting
    }
    private void mouseClicked(MouseEvent mouseEventIn){
//        System.out.println("click");
        if(mouseEventIn.getButton().equals(MouseButton.PRIMARY))
            var.handle(mouseEvent);

    }
    private void mouseReleased(MouseEvent mouseEvent){
        //maybe noting
    }

//    public boolean isClicked(){
////        new KeyEvent
//    }

}
//TODO: maybe the position doesn't change