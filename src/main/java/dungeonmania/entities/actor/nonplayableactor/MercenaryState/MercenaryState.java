package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public interface MercenaryState {

    public boolean canInteract();

    public void updateMovement(MovementBehaviour movementBehaviour);

    public boolean isAlly();

    public void recruitedBy(Player player);

    public void mindcontrol();

    public int bribeAmount();

    public void visitInvisiblePlayer(Player player);

    public boolean isAssassin();
}
