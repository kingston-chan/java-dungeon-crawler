package dungeonmania.entities.item;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;

public abstract class Item extends DungeonObject {

    public void collectedBy(Player player) {
        player.addToInventory(this);
    }

    public abstract boolean playerUse(Player player, Dungeon dungeon);

    @Override
    public boolean isInteractable() {
        return false;
    }
}