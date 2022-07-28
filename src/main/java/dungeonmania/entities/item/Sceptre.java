package dungeonmania.entities.item;

import dungeonmania.entities.actor.player.Player;

public class Sceptre extends Item{
    private int mind_control_duration;
    private int durability;

    public Sceptre(int mind_control_duration,int durability) {
        this.durability = durability;
        this.mind_control_duration = mind_control_duration;
    }

    @Override
    public boolean playerUse(Player player) {
        this.durability -= 1;
        if (this.durability == 0) {
            player.removeFromInventory(this);
        }

        return true;
    }

    public int getMind_control_duration() {
        return mind_control_duration;
    }

}
