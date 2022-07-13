package dungeonmania.entities.actor.nonplayableactor.MercenaryState;


import dungeonmania.entities.actor.nonplayableactor.Mercenary;
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
        mercenary.update(movementBehaviour);
    }

    @Override
    public boolean isAlly() {
        return false;
    }
}
