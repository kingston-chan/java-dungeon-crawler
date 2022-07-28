package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;

public class MindControlState extends AllyState {
    private int counter = 0;

    public MindControlState(Mercenary mercenary) {
        super(mercenary);
    }

    private boolean isUnderControl() {
        if (counter < DungeonManiaController.getDungeon().getIntConfig("mind_control_duration")) {
            counter++;
            return true;
        }
        return false;
    }

    private void mindControlEnds() {
        counter = 0;
        Player player = DungeonManiaController.getDungeon().getPlayer();
        player.reduceAlly();
        getMercenary().setMercenaryState(getMercenary().getEnemyState());
    }

    @Override
    public void movePlayerIsNormal() {
        if (isUnderControl()) {
            super.movePlayerIsNormal();
        } else {
            mindControlEnds();
            getMercenary().movePlayerIsNormal();
        }
    }

    @Override
    public void movePlayerIsInvincible() {
        if (isUnderControl()) {
            super.movePlayerIsInvincible();
        } else {
            mindControlEnds();
            getMercenary().movePlayerIsInvincible();
        }
    }

    @Override
    public void movePlayerIsInvisible() {
        if (isUnderControl()) {
            super.movePlayerIsInvisible();
            return;
        } else {
            mindControlEnds();
            getMercenary().movePlayerIsInvisible();
        }
    }

}
