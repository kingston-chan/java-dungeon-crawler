package dungeonmania.entities.item;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;

public abstract class Item extends DungeonObject {
    public abstract boolean playerUse(Player player);

    @Override
    public boolean accept(Player player) {
        return player.visit(this);
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}