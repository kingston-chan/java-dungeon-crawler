package dungeonmania.entities.item.equiments;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Equipment extends Item{

    @Override
    public boolean playerUse(Player player) {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
