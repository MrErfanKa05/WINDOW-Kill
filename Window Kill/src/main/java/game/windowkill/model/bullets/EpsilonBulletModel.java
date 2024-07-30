package game.windowkill.model.bullets;

import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.multiScene.LocalMultiScene;
import game.windowkill.model.sceneModel.BaseSceneModel;
import game.windowkill.view.bullet.EpsilonBulletView;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class EpsilonBulletModel extends BulletModel implements LocalMultiScene {
    public static ArrayList<EpsilonBulletModel> epsilonBulletModels = new ArrayList<>();
    private BaseSceneModel baseSceneModel;

    public EpsilonBulletModel(double x, double y, int attackHp){
        super(x, y, attackHp);
        epsilonBulletModels.add(this);
        new EpsilonBulletView(id, position);
        baseSceneModel = EpsilonModel.getInstance().getBaseSceneModel();
    }

    public void remove(){
        epsilonBulletModels.remove(this);
        EpsilonBulletView nowEpsilonBulletView = null;
        for(EpsilonBulletView epsilonBulletView : EpsilonBulletView.epsilonBulletViews)
            if(epsilonBulletView.getId().equals(getId()))
                nowEpsilonBulletView = epsilonBulletView;
        EpsilonBulletView.epsilonBulletViews.remove(nowEpsilonBulletView);
    }

    @Override
    public boolean isMultiScene() {
        double left = baseSceneModel.getLeft();
        double right = baseSceneModel.getRight();
        double down = baseSceneModel.getDown();
        double top = baseSceneModel.getTop();
        for(BaseSceneModel model : BaseSceneModel.baseSceneModels){
            double l = model.getLeft();
            double r = model.getRight();
            double t = model.getTop();
            double d = model.getDown();

            if(l == left && r == right && d == down && t == top)
                continue;

            Point2D p1 = new Point2D(l, t);
            Point2D p2 = new Point2D(l, d);
            Point2D p3 = new Point2D(r, t);
            Point2D p4 = new Point2D(r, d);

            if(baseSceneModel.collision(p1) || baseSceneModel.collision(p2) || baseSceneModel.collision(p3) || baseSceneModel.collision(p4))
                return true;
        }
        return false;
    }

    @Override
    public boolean isInsideMultiScene(Point2D point2D) {
        int num = 0;
        for(BaseSceneModel model : BaseSceneModel.baseSceneModels)
            if(model.collision(point2D))
                num++;
        return (num >= 2);
    }
}
