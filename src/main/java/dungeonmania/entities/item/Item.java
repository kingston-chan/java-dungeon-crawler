package dungeonmania.entities.item;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;

public abstract class Item extends DungeonObject {
    @Override
    public boolean isInteractable() {
        return false;
    }

    public abstract boolean playerUse(Player player);
}