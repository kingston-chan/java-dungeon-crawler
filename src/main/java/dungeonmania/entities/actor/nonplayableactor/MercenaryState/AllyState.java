package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public class AllyState implements MercenaryState {

    @Override
    public boolean visitPlayer(Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean acceptPlayer(Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canInteract() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateMovement(MovementBehaviour movementBehaviour) {
        // TODO Auto-generated method stub

    }

}
