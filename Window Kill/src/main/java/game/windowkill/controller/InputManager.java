package game.windowkill.controller;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.awt.event.MouseEvent;


public class InputManager{
    private Scene scene;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean shop;
    private boolean ability;

    private String upString = "w";
    private String downString = "s";
    private String leftString = "a";
    private String rightString = "d";
    private String shopString = "c";
    private String abilityString = "e";

    private Point2D mousePosition = new Point2D(0, 0);

    public InputManager(Scene scene){
        this.scene = scene;
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
    }
    private void mousePressed(MouseEvent mouseEvent){

    }
    private void mouseClicked(MouseEvent mouseEvent){

    }
    private void mouseReleased(MouseEvent mouseEvent){

    }

    public boolean isClicked(){
//        new KeyEvent
    }

}
