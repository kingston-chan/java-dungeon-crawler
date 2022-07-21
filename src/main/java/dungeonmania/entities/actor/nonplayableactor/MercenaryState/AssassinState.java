package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;

public class AssassinState implements MercenaryState{

    private Mercenary assasin;
    private int reconRadius;

    public AssassinState(Mercenary merc, int reconRadius) {
        this.assasin = merc;
        this.reconRadius = reconRadius;
    }

    public boolean checkSurrounding() {
        return true;
    }

    @Override
    public boolean canInteract() {
        return true;
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
    public void recruit() {
        // TODO Auto-generated method stub
        
    }
    
}
