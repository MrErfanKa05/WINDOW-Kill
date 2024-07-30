package game.windowkill.controller.fxmlController;

import game.windowkill.controller.loader.ConfigLoader;
import game.windowkill.model.characterModel.Entity;
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
    RadioButton ares, astrape, cerberus;
    @FXML
    RadioButton aceso, melampus, chiron, athena;
    @FXML
    RadioButton proteus, empusa, dolus;
    @FXML
    Label xp;

    public static boolean a1, a2, a3, d1, d2, d3, d4, c1, c2, c3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aceso.setOnAction(actionEvent -> set(aceso));
        astrape.setOnAction(actionEvent -> set(astrape));
        cerberus.setOnAction(actionEvent -> set(cerberus));

        ares.setOnAction(actionEvent -> set(ares));
        melampus.setOnAction(actionEvent -> set(melampus));
        chiron.setOnAction(actionEvent -> set(chiron));
        athena.setOnAction(actionEvent -> set(athena));

        proteus.setOnAction(actionEvent -> set(proteus));
        empusa.setOnAction(actionEvent -> set(empusa));
        dolus.setOnAction(actionEvent -> set(dolus));

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
        ConfigLoader loader = ConfigLoader.getInstance();

        if(radioButton.equals(ares) && !a1){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getA1()) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfAres);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getA1());
                a1 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(astrape) && !a2){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getA2() && a1) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfAstrape);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getA2());
                a2 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(cerberus) && !a3){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getA3() && a2) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfCerberus);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getA3());
                a3 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }

        if(radioButton.equals(aceso) && !d1){
//            System.out.println("1");
            if(EpsilonModel.getInstance().getXP() >= loader.getD1()) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfAceso);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getD1());
                d1 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(melampus) && !d2){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getD2() && d1) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfMelampus);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getD2());
                d2 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(chiron) && !d3){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getD3() && d2) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfChiron);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getD3());
                d3 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(athena) && !d4){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getD4() && d2) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfAthena);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getD4());
                d4 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }

        if(radioButton.equals(proteus) && !c1){
//            System.out.println("3");
            if(EpsilonModel.getInstance().getXP() >= loader.getC1()) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfProteus);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getC1());
                c1 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(empusa) && !c2){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getC2() && c1) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfEmpusa);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getC2());
                c2 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }
        if(radioButton.equals(dolus) && !c3){
//            System.out.println("2");
            if(EpsilonModel.getInstance().getXP() >= loader.getC3() && c2) {
                EpsilonModel.getInstance().setSkillState(SkillState.WritOfDolus);
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - loader.getC3());
                c3 = true;
            }
            else{
                radioButton.setSelected(false);
                return;
            }
        }

        astrape.setSelected(false);
        ares.setSelected(false);
        cerberus.setSelected(false);
        aceso.setSelected(false);
        athena.setSelected(false);
        melampus.setSelected(false);
        chiron.setSelected(false);
        proteus.setSelected(false);
        empusa.setSelected(false);
        dolus.setSelected(false);

        radioButton.setSelected(true);
        setXp();
    }
}
