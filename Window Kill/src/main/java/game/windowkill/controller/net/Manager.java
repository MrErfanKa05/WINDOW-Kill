package game.windowkill.controller.net;

import game.windowkill.controller.Constant;
import game.windowkill.controller.fxmlController.Controller;
import game.windowkill.controller.fxmlController.HomeController;
import game.windowkill.controller.fxmlController.NameController;
import game.windowkill.controller.loader.SceneLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import static game.windowkill.controller.Constant.*;

public class Manager {
    private static Manager manager;

    public static Manager getInstance() {
        if(manager == null)
            manager = new Manager();
        return manager;
    }

    private Popup popup = new Popup();
    private Pane pane = new Pane();
    private Button button = new Button("ok");
    private Label label = new Label("not connected");

    private Socket socket;
    private Messenger messenger;
    private String name;
    private int xp = 400;
    private Manager() {
        Controller.getStage().setScene(SceneLoader.getMenu());
        pane.setPrefWidth(100);
        pane.setPrefHeight(100);
        popup.getContent().add(pane);

        popup.setX(SCREEN_WIDTH/2 + 200);
        popup.setY(SCREEN_HEIGHT/2 + 200);
        pane.setPrefWidth(100);
        pane.setPrefHeight(100);

        pane.setBackground(Background.fill(Color.WHITE));


        label.setTranslateX(18);
        label.setTranslateY(25);

        button.setTranslateX(25);
        button.setTranslateY(50);
        button.setOnAction(actionEvent -> {
            popup.hide();
        });

        pane.getChildren().add(label);
        pane.getChildren().add(button);
    }

    public void createUser(String name){
        messenger.write("createUser");
        messenger.write(name);
        if(messenger.read().equals("duplicateName")){
            NameController.setError("this name has taken already");
            Controller.getStage().setScene(SceneLoader.getName());
            return;
        }
//        messenger.write("getInformation");
//        name = messenger.read();
//        xp = Integer.parseInt(messenger.read());
        Controller.getStage().setScene(SceneLoader.getHome());
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(FPS), actionEvent -> {
        if(messenger.hasNext()){

        }
    }));

    public ArrayList<String> squads = new ArrayList<>();

    public ArrayList<String> getSquad(){
        messenger.write("showSquad");
        String s = messenger.read();
        Scanner sc = new Scanner(s);
        squads.clear();
        while (sc.hasNextLine()){
            String name = sc.nextLine();
            squads.add(name);
        }
        return squads;
    }

    public void gotoSquad(){
        messenger.write("hasSquad");
        String s = messenger.read();
        if(s.equals("no")){
            HomeController.type = "you have no squad";
            Controller.getStage().setScene(SceneLoader.getHome());
            return;
        }
        Controller.getStage().setScene(SceneLoader.getSquad());
    }

    public void newGame(String name){
        messenger.write("newGame");
        messenger.write(name);
        if(messenger.read().equals("no")){
            Controller.getStage().setScene(SceneLoader.getSquad());
            return;
        }
//        Controller.getStage().
    }

    public void wantToJoin(String name){
        messenger.write("wantToJoinSquad");
        messenger.write(name);
        if(messenger.read().equals("waitUntilRespond")){
            HomeController.type = "wait Until Respond";
        }
        else
            HomeController.type = "request forwarded";
        Controller.getStage().setScene(SceneLoader.getHome());
    }

    public void connect(){
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9000);
        } catch (IOException e) {
            popup.show(Controller.getStage());
            return;
        }
        if(!socket.isConnected())
            popup.show(Controller.getStage());
        try {
            messenger = new Messenger(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Controller.getStage().setScene(SceneLoader.getName());
//        Controller.getStage().setScene(SceneLoader.getHome());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void close(){
        messenger.close();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Controller.getStage().setScene(SceneLoader.getMenu());
    }

    public void createSquad(String text) {
        messenger.write("createSquad");
        messenger.write(String.valueOf(xp));
        messenger.write(text);
        if(messenger.read().equals("ok")){
            xp -= 100;
            Controller.getStage().setScene(SceneLoader.getSquad());
            return;
        }
        Controller.getStage().setScene(SceneLoader.getHome());
    }

    public ArrayList<String> enemy = new ArrayList<>();

    public ArrayList<String> getEnemy() {
        messenger.write("getEnemy");
        if(messenger.read().equals("no")){
            return new ArrayList<>();
        }
        String s = messenger.read();
        enemy.clear();
        Scanner sc = new Scanner(s);
        while (sc.hasNext()){
            enemy.add(sc.next());
        }
        return enemy;
    }

    public String getView() {
        String s = messenger.read();
        return s;
    }

    public void sendView(String s){
        messenger.write(s);
    }
}
