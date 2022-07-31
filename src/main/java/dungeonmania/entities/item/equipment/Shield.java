package dungeonmania.entities.item.equipment;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public class Shield extends Protection {

    public Shield(int defence, int durability) {
        super(defence,durability);
    }

    @Override
    public Item playerEquip(Player player) {
        player.increaseAdditiveDefence(this.getDefence());
        if (reduceDurability(1)) {
            player.removeFromInventory(this);
        }
        return this;
    }

}
