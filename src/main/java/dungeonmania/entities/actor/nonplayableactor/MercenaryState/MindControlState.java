package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;

public class MindControlState extends AllyState {
    private int counter = 1;
    private int duration;

    public MindControlState(Mercenary mercenary, int duration) {
        super(mercenary);
        this.duration = duration;
    }

    private void checkIsStillUnderControl() {
        if (counter < duration) {
            counter++;
        } else {
            mindControlEnds();
        }
    }

    private void mindControlEnds() {
        counter = 0;
        Player player = DungeonManiaController.getDungeon().getPlayer();
        player.reduceAlly();
        getMercenary().setMercenaryState(getMercenary().getEnemyState());
    }

    @Override
    public void movePlayerIsNormal() {
        super.movePlayerIsNormal();
        checkIsStillUnderControl();
    }

    @Override
    public void movePlayerIsInvincible() {
        super.movePlayerIsInvincible();
        checkIsStillUnderControl();
    }

    @Override
    public void movePlayerIsInvisible() {
        super.movePlayerIsInvisible();
        checkIsStillUnderControl();
    }
}
