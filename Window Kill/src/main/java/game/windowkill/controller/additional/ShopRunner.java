package game.windowkill.controller.additional;

import game.windowkill.controller.Update;
import game.windowkill.model.characterModel.EpsilonModel;

public class ShopRunner implements Runnable{
    public enum Shop{
        Heal, Empower, Banish, Slaughter, Slumber, Dismay, Neutral
    }

    public static Shop shop = Shop.Neutral;

    public ShopRunner(){

    }

    @Override
    public synchronized void run() {
        if(shop.equals(Shop.Heal)){
            if(EpsilonModel.getInstance().getXP() >= 50){
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - 50);
                EpsilonModel.getInstance().setHp(EpsilonModel.getInstance().getHp() + 10);
                try {
                    Thread.sleep(300_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(shop.equals(Shop.Empower)){
            if(EpsilonModel.getInstance().getXP() >= 75){
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - 75);
                Update.multiShot = true;
                try {
                    Thread.sleep(300_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if (shop.equals(Shop.Banish)) {
            if(EpsilonModel.getInstance().getXP() >= 100){
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - 100);
                Update.bigImpact = true;
                try {
                    Thread.sleep(300_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(shop.equals(Shop.Slaughter)){
            if(EpsilonModel.getInstance().getXP() >= 200){
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - 200);
                Update.bigShot = true;
                try {
                    Thread.sleep(300_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(shop.equals(Shop.Slumber)){
            if(EpsilonModel.getInstance().getXP() >= 150){
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - 150);
                Update.stopGame = true;
                try {
                    Thread.sleep(300_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else  if(shop.equals(Shop.Dismay)){
            if(EpsilonModel.getInstance().getXP() >= 120){
                EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - 120);
                Update.farEnemy = true;
                try {
                    Thread.sleep(300_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
