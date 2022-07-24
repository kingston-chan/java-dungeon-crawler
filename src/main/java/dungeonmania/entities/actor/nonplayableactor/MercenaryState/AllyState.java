package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;

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
    public void recruit() {

    }

    @Override
    public void mindcontrol() {
        // TODO Auto-generated method stub
        
    }
}
