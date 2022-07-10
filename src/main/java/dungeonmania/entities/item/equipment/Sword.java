package dungeonmania.entities.item.equipment;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public class Sword extends Weapon {
    public Sword(int attack, int durability) {
        super(attack, durability);
    }

    @Override
    public Item playerEquip(Player player) {
        player.increaseAdditiveAttack(getAttack());
        if (reduceDurability(1)) {
            player.removeFromInventory(this);
        }
        return this;
    }
}
