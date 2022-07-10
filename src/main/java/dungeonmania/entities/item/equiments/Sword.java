package dungeonmania.entities.item.equiments;

import dungeonmania.entities.actor.player.Player;

public class Sword extends Equipment {

    @Override
    public boolean provideAttack(Player player) {
        // Use this equipment once
        this.setDurability(this.getDurability()-1);
        // this should be saved some variable in player

        return true;
    }

    @Override
    public boolean provideDefense(Player player) {
        return false;
    }

}
