package game.windowkill.controller.additional;

import game.windowkill.controller.Constant;
import game.windowkill.controller.Update;
import game.windowkill.controller.fxmlController.SkillTreeController;
import game.windowkill.model.characterModel.EpsilonModel;
import game.windowkill.model.characterModel.SkillState;
import game.windowkill.model.sceneModel.BaseSceneModel;

import java.util.ArrayList;
import java.util.Random;

import static game.windowkill.controller.Constant.CHANGE_FRAME;
import static game.windowkill.controller.Constant.SKILL_TREE_XP;

public class SkillTree implements Runnable{

    private long time = 0;

    public SkillTree(){

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        SkillState skillState = EpsilonModel.getInstance().getSkillState();
        Update.onSkillStates.clear();
        long now = System.currentTimeMillis();
        if(skillState.equals(SkillState.Neutral))
            return;
        if(EpsilonModel.getInstance().getXP() >= SKILL_TREE_XP && now - time >= 300_000)
            return;
        EpsilonModel.getInstance().setXP(EpsilonModel.getInstance().getXP() - SKILL_TREE_XP);
        time = now;
        if(skillState.equals(SkillState.WritOfAres)){
            EpsilonModel.getInstance().setAttackHp(3);
            Update.onSkillStates.add(SkillState.WritOfAres);
        }
        if(skillState.equals(SkillState.WritOfAstrape)){
            Update.onSkillStates.add(SkillState.WritOfAstrape);
        }
        if(skillState.equals(SkillState.WritOfCerberus)){

            Update.onSkillStates.add(SkillState.WritOfCerberus);
        }


        if(skillState.equals(SkillState.WritOfAceso)){
            Update.onSkillStates.add(SkillState.WritOfAceso);
        }
        if(skillState.equals(SkillState.WritOfMelampus)){
            Update.onSkillStates.add(SkillState.WritOfMelampus);
        }
        if(skillState.equals(SkillState.WritOfChiron)){
            Update.onSkillStates.add(SkillState.WritOfChiron);
        }
        if(skillState.equals(SkillState.WritOfAthena)){
            BaseSceneModel.setChangeFrame(CHANGE_FRAME - CHANGE_FRAME*20 / 100);
            Update.onSkillStates.add(SkillState.WritOfAthena);
        }


        if(skillState.equals(SkillState.WritOfProteus)){
            EpsilonModel.getInstance().addVertex();
            Update.onSkillStates.add(SkillState.WritOfProteus);
        }
        if(skillState.equals(SkillState.WritOfEmpusa)){
            Constant.changeEpsilonRadius(10);
            Update.onSkillStates.add(SkillState.WritOfEmpusa);
        }
        if(skillState.equals(SkillState.WritOfDolus)){
            //todo :
            ArrayList<SkillState> skillStates = new ArrayList<>();
            if(SkillTreeController.a1)
                skillStates.add(SkillState.WritOfAres);
            if(SkillTreeController.a2)
                skillStates.add(SkillState.WritOfAstrape);
            if(SkillTreeController.a3)
                skillStates.add(SkillState.WritOfCerberus);
            if(SkillTreeController.d1)
                skillStates.add(SkillState.WritOfAceso);
            if(SkillTreeController.d2)
                skillStates.add(SkillState.WritOfMelampus);
            if(SkillTreeController.d3)
                skillStates.add(SkillState.WritOfChiron);
            if(SkillTreeController.d4)
                skillStates.add(SkillState.WritOfAthena);
            if(SkillTreeController.c1)
                skillStates.add(SkillState.WritOfProteus);
            if(SkillTreeController.c2)
                skillStates.add(SkillState.WritOfEmpusa);
            if(SkillTreeController.c3)
                skillStates.add(SkillState.WritOfDolus);

            SkillState skillState1;
            SkillState skillState2;

            int rnd;
            rnd = new Random().nextInt(skillStates.size());
            skillState1 = skillStates.get(rnd);
            skillStates.remove(skillState1);
            rnd = new Random().nextInt(skillStates.size());
            skillState2 = skillStates.get(rnd);

            Update.onSkillStates.add(skillState2);
            Update.onSkillStates.add(skillState1);

            EpsilonModel.getInstance().setSkillState(SkillState.WritOfDolus);
        }

        try {
            Thread.sleep(300_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        EpsilonModel.getInstance().setSkillState(SkillState.Neutral);
    }
}
