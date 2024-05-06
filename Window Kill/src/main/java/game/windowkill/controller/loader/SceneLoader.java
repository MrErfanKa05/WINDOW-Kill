package game.windowkill.controller.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;

public class SceneLoader {
    private final static String setting = "src/main/resources/game/windowkill/fxmls/Setting.fxml";
    private final static String skillTree = "src/main/resources/game/windowkill/fxmls/SkillTree.fxml";
    private final static String menu = "src/main/resources/game/windowkill/fxmls/Menu.fxml";
    private final static String tutorial = "src/main/resources/game/windowkill/fxmls/Tutorial.fxml";
    private final static String gameOver = "src/main/resources/game/windowkill/fxmls/GameOver.fxml";
    public static Scene getSetting(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File(setting).toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load(), 700, 700);
            return scene;
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return null;
    }
    public static Scene getGameOver(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File(gameOver).toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load(), 700, 700);
            return scene;
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return null;
    }
    public static Scene getMenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File(menu).toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load(), 700, 700);
            return scene;
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return null;
    }
    public static Scene getTutorial(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File(tutorial).toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load(), 700, 700);
            return scene;
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return null;
    }
    public static Scene getSkillTree(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File(skillTree).toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load(), 700, 700);
            return scene;
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return null;
    }
}
