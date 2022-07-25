package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public class AllyState implements MercenaryState {
    @Override
    public boolean canInteract() {
        return false;
    }

    @Override
    public void updateMovement(MovementBehaviour movementBehaviour) {
        // do nothing
    }

    @Override
    public boolean isAlly() {
        return true;
    }

    @Override
    public void recruitedBy(Player player) {

    }

    @Override
    public void mindcontrol() {
        // TODO Auto-generated method stub
    }

    @Override
    public int bribeAmount() {
        return 0;
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
    }

    @Override
    public boolean isAssassin() {
        return false;
    }
}
