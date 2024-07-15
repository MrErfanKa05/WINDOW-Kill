package game.windowkill.model.attack;

import game.windowkill.model.characterModel.Entity;
import game.windowkill.model.characterModel.collision.ModeInitialize;
import javafx.geometry.Point2D;

import static game.windowkill.controller.Constant.MELEE_DISTANCE;

public interface MeleeAttack extends ModeInitialize {
    default void meleeAttack(Entity base, Entity entity) {
        Point2D point = getIntersect(entity);
        for(Point2D point2D : base.getVertices()){
            if(getDistance(point, point2D) <= MELEE_DISTANCE) {
                entity.decreaseHearth(base.getAttackHp());
                return;
            }
        }
    }
}
