package game.windowkill.controller.additional;

import game.windowkill.controller.fxmlController.Controller;
import game.windowkill.model.characterModel.EpsilonModel;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

public class Shop {
    private Popup popup = new Popup();
    private Pane pane = new Pane();
    private Button heal, empower, banish, dismay, slumber, slaughter;
    public Shop(){
        popup.getContent().add(pane);
        heal = new Button("O' Apollo");
        empower = new Button("O’ Athena");
        banish = new Button("O' Hephaestus");
        dismay = new Button("O’ Deimos");
        slaughter = new Button("O’ Phonoi");
        slumber = new Button("O’Hypnos");

        init();
    }

    public void init(){
        popup.setWidth(300);
        popup.setHeight(300);

        heal.setOnAction(actionEvent -> {
            if(EpsilonModel.getInstance().getXP() >= 50){
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - 50);
                EpsilonModel.getInstance().setHp(EpsilonModel.getInstance().getHp() + 10);
            }
        });

    }

    public void showShop(){
        popup.show(Controller.getStage());
    }

    public void hideShop(){
        popup.hide();
    }
}
