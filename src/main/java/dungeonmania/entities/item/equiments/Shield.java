package dungeonmania.entities.item.equiments;

import dungeonmania.entities.actor.player.Player;

public class Shield extends Equipment{

    @Override
    public boolean provideAttack(Player player) {
        return false;
    }

    @Override
    public boolean provideDefense(Player player) {
        // Use this equipment once
        this.setDurability(this.getDurability()-1);
        // this should be saved some variable in player
        return true;
    }

}
