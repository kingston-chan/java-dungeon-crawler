package dungeonmania.entities.actor.player.interactables;

import dungeonmania.entities.actor.player.Player;

public interface InteractBehaviour extends java.io.Serializable {
    public boolean interact(Player player, String interactingWithId);
}
