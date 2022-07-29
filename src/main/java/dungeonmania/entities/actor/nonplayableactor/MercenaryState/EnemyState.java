package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveAwayFromPlayer;
import dungeonmania.behaviours.movement.MoveRandomly;

public class EnemyState implements MercenaryState {
    private Mercenary mercenary;

    public EnemyState(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    public Mercenary getEnemy() {
        return this.mercenary;
    }

    @Override
    public boolean canInteract() {
        return true;
    }

    @Override
    public boolean isAlly() {
        return false;
    }

    @Override
    public void recruitedBy(Player player) {
        player.addAlly();
        mercenary.setMercenaryState(mercenary.getAllyState());
    }

    @Override
    public void mindcontrol() {
        mercenary.setMercenaryState(mercenary.getMindControlState());
    }

    @Override
    public int bribeAmount() {
        return DungeonManiaController.getDungeon().getIntConfig("bribe_amount");
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
    }

    @Override
    public boolean isAssassin() {
        return false;
    }

    private void tryMove() {
        if (!mercenary.isStuck())
            mercenary.doMove();
    }

    @Override
    public void movePlayerIsNormal() {
        mercenary.setCurrentMovement(mercenary.getDefaultMovement());
        tryMove();
    }

    @Override
    public void movePlayerIsInvincible() {
        mercenary.setCurrentMovement(new MoveAwayFromPlayer());
        tryMove();
    }

    @Override
    public void movePlayerIsInvisible() {
        mercenary.setCurrentMovement(new MoveRandomly());
        tryMove();
    }
}
