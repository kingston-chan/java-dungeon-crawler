package dungeonmania.entities.item.equipment;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public class MidnightArmor extends Protection{
    private int attack;

    public MidnightArmor(int attack, int defence, int durability) {
        super(defence, durability);
        this.attack = attack;
    }

    @Override
    public Item playerEquip(Player player) {
        player.increaseAdditiveAttack(attack);
        player.increaseAdditiveDefence(this.getDefence());
        return this;
    }

}
