package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;

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
        if (counter < DungeonManiaController.getDungeon().getIntConfig("mind_control_duration")){
            counter++;
        } else {
            mercenary.setMercenaryState(mercenary.getEnemyState());
            mercenary.setCurrentMovement(movementBehaviour);
            counter = 0;
        }
    }

    @Override
    public boolean isAlly() {
        return true;
    }

    @Override
    public void recruitedBy(Player player) {
    }

    @Override
    public void mindcontrol() {
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
