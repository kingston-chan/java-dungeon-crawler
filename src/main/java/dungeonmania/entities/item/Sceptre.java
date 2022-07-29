package dungeonmania.entities.item;

import dungeonmania.entities.actor.player.Player;

public class Sceptre extends Item {
    private int durability;

    public Sceptre(int durability) {
        this.durability = durability;
    }

    @Override
    public boolean playerUse(Player player) {
        this.durability -= 1;
        if (this.durability == 0) {
            player.removeFromInventory(this);
        }

        return true;
    }

}
