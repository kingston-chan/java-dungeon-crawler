package dungeonmania.entities.actor.nonplayableactor.MercenaryState;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public interface MercenaryState {
    public boolean visitPlayer(Player player);

    public boolean acceptPlayer(Player player);

    public boolean canInteract();

    public void updateMovement(MovementBehaviour movementBehaviour);
}
