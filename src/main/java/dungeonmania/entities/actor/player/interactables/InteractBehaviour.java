package dungeonmania.entities.actor.player.interactables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;

public interface InteractBehaviour {
    public boolean interact(Dungeon dungeon, Player player, String interactingWithId);
}
