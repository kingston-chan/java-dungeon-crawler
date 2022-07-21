package dungeonmania.entities.item.equipment;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public class Sceptre extends Equipment{
    private int mind_control_duration;

    public Sceptre(int mind_control_duration,int durability) {
        super(durability);
        this.mind_control_duration = mind_control_duration;
    }

    @Override
    public Item playerEquip(Player player) {
        if (reduceDurability(1)) {
            player.removeFromInventory(this);
        }

        return null;
    }
}
