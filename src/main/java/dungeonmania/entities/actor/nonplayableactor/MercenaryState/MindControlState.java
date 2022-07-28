package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MovementBehaviour;
<<<<<<< HEAD
=======
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
>>>>>>> e5ce0254cca0a742955f4177de29731bb2367a9a
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
<<<<<<< HEAD
        // TODO Auto-generated method stub

=======
        if (counter < DungeonManiaController.getDungeon().getIntConfig("mind_control_duration")){
            counter++;
        } else {
            mercenary.setMercenaryState(mercenary.getEnemyState());
            mercenary.setCurrentMovement(movementBehaviour);
            counter = 0;
            Player player = DungeonManiaController.getDungeon().getPlayer();
            player.reduceAlly();
        }
>>>>>>> e5ce0254cca0a742955f4177de29731bb2367a9a
    }

    @Override
    public boolean isAlly() {
        return true;
    }

    @Override
    public void recruitedBy(Player player) {
<<<<<<< HEAD
        // TODO Auto-generated method stub

=======
>>>>>>> e5ce0254cca0a742955f4177de29731bb2367a9a
    }

    @Override
    public void mindcontrol() {
<<<<<<< HEAD
        // TODO Auto-generated method stub

=======
>>>>>>> e5ce0254cca0a742955f4177de29731bb2367a9a
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
