package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public class MindControlState implements MercenaryState {
    private int counter;

    @Override
    public boolean canInteract() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateMovement(MovementBehaviour movementBehaviour) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isAlly() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void recruitedBy(Player player) {
        // TODO Auto-generated method stub

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
