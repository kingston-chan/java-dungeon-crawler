package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.FollowPlayer;
import dungeonmania.behaviours.movement.MovementBehaviour;

public class EnemyState implements MercenaryState {

    private Mercenary mercenary;

    public EnemyState(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    @Override
    public boolean canInteract() {
        return true;
    }

    @Override
    public void updateMovement(MovementBehaviour movementBehaviour) {
        mercenary.setCurrentMovement(movementBehaviour);
    }

    @Override
    public boolean isAlly() {
        return false;
    }

    @Override
    public void recruitedBy(Player player) {
        player.addAlly();
        mercenary.setMercenaryState(mercenary.getAllyState());
        MovementBehaviour allyMovement = new FollowPlayer();
        mercenary.setCurrentMovement(allyMovement);
    }

    @Override
    public void mindcontrol() {
        mercenary.setMercenaryState(mercenary.getMindcontrolState());
        MovementBehaviour mindcontrolMovement = new FollowPlayer();
        mercenary.setCurrentMovement(mindcontrolMovement);
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
}
