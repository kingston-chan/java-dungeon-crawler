package dungeonmania.entities.item.equipment;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public class Bow extends Weapon {
    public Bow(int attack, int durability) {
        super(attack, durability);
    }

    @Override
    public Item playerEquip(Player player) {
        player.increaseMultiplicativeAttack(getAttack());
        if (reduceDurability(1)) {
            player.removeFromInventory(this);
        }
        return this;
    }
}
