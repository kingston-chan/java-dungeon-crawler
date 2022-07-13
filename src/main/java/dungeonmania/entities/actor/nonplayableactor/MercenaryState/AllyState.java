package dungeonmania.entities.actor.nonplayableactor.MercenaryState;


import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.behaviours.movement.MovementBehaviour;

import dungeonmania.entities.actor.player.Player;

public class AllyState implements MercenaryState {

    private Mercenary mercenary;

    public AllyState(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    @Override
    public boolean visitPlayer(Player player) {
        return false;
    }

    @Override
    public boolean acceptPlayer(Player player) {
        return true;
    }

    @Override
    public boolean canInteract() {
        return false;
    }

    @Override
    public void updateMovement(MovementBehaviour movementBehaviour) {
        mercenary.update(movementBehaviour);
    }

}
