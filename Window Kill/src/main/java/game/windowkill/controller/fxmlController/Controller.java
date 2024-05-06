package game.windowkill.controller.fxmlController;

import game.windowkill.controller.DataHolder;
import game.windowkill.controller.loader.SceneLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller {
    public static Stage getStage(){
        return DataHolder.stage;
    }
    public void setScene(Scene scene){
        DataHolder.stage.setScene(scene);
    }
    public void setSetting(){
        setScene(SceneLoader.getSetting());
    }
    public void setMenu(){
        setScene(SceneLoader.getMenu());
    }
    public void setTutorial(){
        setScene(SceneLoader.getTutorial());
    }
    public void setSkillTree(){
        setScene(SceneLoader.getSkillTree());
    }
    public void getGameOver(){
        setScene(SceneLoader.getGameOver());
    }
}
