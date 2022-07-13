package dungeonmania.entities.actor.player.interactables;

import dungeonmania.entities.actor.player.Player;

public interface InteractBehaviour {
    public boolean interact(Player player, String interactingWithId);
}
