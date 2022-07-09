package dungeonmania.entities.item;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;

public abstract class Item extends DungeonObject {
    private int Durability;

    public abstract boolean playerUse(Player player);
    public abstract boolean playerEquip(Player player);

    public int getDurability() {
        return this.Durability;
    }

    public void setDurability(int durability) {
        this.Durability = durability;
    }


    @Override
    public boolean isInteractable() {
        return false;
    }
}