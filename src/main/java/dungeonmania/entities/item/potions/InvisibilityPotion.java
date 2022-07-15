package dungeonmania.entities.item.potions;

import dungeonmania.entities.actor.player.Player;

public class InvisibilityPotion extends Potion {

    public InvisibilityPotion(int duration) {
        super(duration);
    }

    @Override
    public void consumedBy(Player player) {
        player.setPlayerState(player.getInvisibleState());
    }

}
