package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;

public interface MercenaryState {

    public boolean canInteract();

    public void updateMovement(MovementBehaviour movementBehaviour);

    public boolean isAlly();
}
