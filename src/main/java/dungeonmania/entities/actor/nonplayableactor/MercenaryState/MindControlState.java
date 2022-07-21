package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;

public class MindControlState implements MercenaryState {

    private Mercenary mercenary;
    private int counter = 0;

    public MindControlState(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    @Override
    public boolean canInteract() {
        return false;
    }

    @Override
    public void updateMovement(MovementBehaviour movementBehaviour) {
        if (counter < DungeonManiaController.getDungeon().getConfig("mind_control_duration")){
            counter++;
        } else {
            mercenary.setMercenaryState(mercenary.getEnemyState());
            counter = 0;
        }
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
    }

}
