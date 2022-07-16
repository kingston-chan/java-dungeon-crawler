package dungeonmania.entities.item.equipment;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public class Shield extends Equipment {
    private int defence;

    public Shield(int defence, int durability) {
        super(durability);
        this.defence = defence;
    }

    @Override
    public Item playerEquip(Player player) {
        player.increaseAdditiveDefence(defence);
        if (reduceDurability(1)) {
            player.removeFromInventory(this);
        }
        return this;
    }

}
