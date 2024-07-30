package game.windowkill.controller.additional;

import game.windowkill.controller.fxmlController.Controller;
import game.windowkill.model.characterModel.EpsilonModel;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

public class ShopChooser {
    private Popup popup = new Popup();
    private Pane pane = new Pane();
    private Button heal, empower, banish, dismay, slumber, slaughter, back;
    public ShopRunner shopRunner = new ShopRunner();
    public Thread thread;
    public ShopChooser(){
        popup.getContent().add(pane);
        heal = new Button("O' Apollo");
        empower = new Button("O’ Athena");
        banish = new Button("O' Hephaestus");
        dismay = new Button("O’ Deimos");
        slaughter = new Button("O’ Phonoi");
        slumber = new Button("O’Hypnos");
        back = new Button("Back");

        thread = new Thread(shopRunner);
        init();
    }

    public void init(){
        popup.setWidth(300);
        popup.setHeight(300);

        heal.setTranslateX(20);
        heal.setTranslateY(20);

        empower.setTranslateX(100);
        empower.setTranslateY(20);

        banish.setTranslateX(180);
        banish.setTranslateY(20);

        slumber.setTranslateX(160);
        slumber.setTranslateY(20);

        slaughter.setTranslateX(20);
        slaughter.setTranslateY(170);

        dismay.setTranslateX(120);
        dismay.setTranslateY(170);

        back.setTranslateX(220);
        back.setTranslateY(170);


        showShop();

        heal.setOnAction(actionEvent -> {
            ShopRunner.shop = ShopRunner.Shop.Heal;
            thread.start();
        });
        empower.setOnAction(actionEvent -> {
            ShopRunner.shop = ShopRunner.Shop.Empower;
            thread.start();
        });
        banish.setOnAction(actionEvent -> {
            ShopRunner.shop = ShopRunner.Shop.Banish;
            thread.start();
        });
        slaughter.setOnAction(actionEvent -> {
            ShopRunner.shop = ShopRunner.Shop.Slaughter;
            thread.start();
        });
        slumber.setOnAction(actionEvent -> {
            ShopRunner.shop = ShopRunner.Shop.Slumber;
            thread.start();
        });
        dismay.setOnAction(actionEvent -> {
            ShopRunner.shop = ShopRunner.Shop.Dismay;
            thread.start();
        });
        back.setOnAction(actionEvent -> hideShop());

    }

    public void showShop(){
        popup.show(Controller.getStage());
    }

    public void hideShop(){
        popup.hide();
    }
}
