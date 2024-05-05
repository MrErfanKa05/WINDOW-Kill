package game.windowkill.game;

import game.windowkill.controller.DataHolder;
import game.windowkill.controller.Update;
import game.windowkill.view.MotionSceneView;
//import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.KeyEvent;

import static game.windowkill.controller.DataHolder.stage;

public class WindowKill {
    public WindowKill(Stage stage){
        DataHolder.stage = stage;
        init();
    }
    public void init(){
        try {
            Robot robot = new Robot();

            // فشار دادن کلید ویندوز + D برای نمایش دسکتاپ (minimize همه پنجره‌ها)
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_WINDOWS);

        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setTitle("Window Kill");

        stage.setResizable(false);
        stage.setIconified(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setMaximized(false);

        MotionSceneView.getInstance().init();
        new Update();

        stage.show();
    }
}
