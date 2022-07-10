package dungeonmania.entities.item.equipment;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Equipment extends Item {

    private int durability;

    public Equipment(int durability) {
        this.durability = durability;
    }

    /**
     * 
     * @param reduceAmount
     * @return if durability below zero
     */
    public boolean reduceDurability(int reduceAmount) {
        this.durability -= reduceAmount;
        return this.durability <= 0;
    }

    public abstract Item playerEquip(Player player);

    @Override
    public boolean playerUse(Player player, Dungeon dungeon) {
        return false;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

}
