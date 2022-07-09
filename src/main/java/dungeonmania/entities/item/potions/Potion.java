package dungeonmania.entities.item.potions;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Potion extends Item {

    @Override
    public boolean playerUse(Player player) {
        player.addPotion(this);
        return true;
    }

    @Override
    public boolean playerEquip(Player player) {
        return false;
    }
}
