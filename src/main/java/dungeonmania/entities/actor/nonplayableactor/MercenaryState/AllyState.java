package dungeonmania.entities.actor.nonplayableactor.MercenaryState;


import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.behaviours.movement.MovementBehaviour;

public class AllyState implements MercenaryState {

    private Mercenary mercenary;

    public AllyState(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

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
}
