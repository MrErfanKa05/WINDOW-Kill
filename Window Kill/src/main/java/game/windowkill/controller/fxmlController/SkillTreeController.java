package game.windowkill.controller.fxmlController;

import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.SkillState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

public class SkillTreeController extends Controller implements Initializable {
    @FXML
    Button back;
    @FXML
    RadioButton ares;
    @FXML
    RadioButton aceso;
    @FXML
    RadioButton proteus;
    @FXML
    Label xp;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aceso.setOnAction(actionEvent -> set(aceso));
        ares.setOnAction(actionEvent -> set(ares));
        proteus.setOnAction(actionEvent -> set(proteus));
        back.setOnAction(actionEvent -> Back());
        setXp();
    }
    public void setXp(){
        xp.setText(String.valueOf(EpsilonModel.getInstance().getXP()));
    }
    private void Back(){
        setMenu();
    }
    public void set(RadioButton radioButton){
//        System.out.println(radioButton + " " + EpsilonModel.getInstance().getXP());
        if(radioButton.equals(aceso)){
//            System.out.println("1");
            if(EpsilonModel.getInstance().getXP() >= 500)
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfAceso);
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(ares)){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= 750)
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfAres);
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(proteus)){
//            System.out.println("3");
            if(EpsilonModel.getInstance().getXP() >= 1000)
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfProteus);
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(ares.equals(radioButton))
            ares.setSelected(true);
        else ares.setSelected(false);
        if(aceso.equals(radioButton))
            aceso.setSelected(true);
        else aceso.setSelected(false);
        if(proteus.equals(radioButton))
            proteus.setSelected(true);
        else proteus.setSelected(false);
        setXp();
    }
}
