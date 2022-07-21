package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.entities.actor.nonplayableactor.Mercenary;
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
    public void recruit() {
        mercenary.setMercenaryState(mercenary.getAllyState());
        MovementBehaviour allyMovement = new FollowPlayer();
        mercenary.setDefaultMovement(allyMovement);
        mercenary.setCurrentMovement(allyMovement);
    }

    @Override
    public void mindcontrol() {
        mercenary.setMercenaryState(mercenary.getMindcontrolState());
        MovementBehaviour mindcontrolMovement = new FollowPlayer();
        mercenary.setDefaultMovement(mindcontrolMovement);
        mercenary.setCurrentMovement(mindcontrolMovement);
    }
}
