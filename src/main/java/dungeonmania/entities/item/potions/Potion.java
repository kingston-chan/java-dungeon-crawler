package dungeonmania.entities.item.potions;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Potion extends Item {
    private int duration;

    public Potion(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public abstract void consumedBy(Player player);

    @Override
    public boolean playerUse(Player player) {
        player.usePotion(this);
        player.removeFromInventory(this);
        return true;
    }

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }
}
