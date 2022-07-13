package dungeonmania.entities.item.potions;

import dungeonmania.entities.actor.player.Player;

public class InvincibilityPotion extends Potion {

    public InvincibilityPotion(int duration) {
        super(duration);
    }

    @Override
    public void consumedBy(Player player) {
        player.setPlayerState(player.getInvincibleState());
    }
}
