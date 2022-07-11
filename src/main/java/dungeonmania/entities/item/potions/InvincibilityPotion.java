package dungeonmania.entities.item.potions;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public class InvincibilityPotion extends Potion {
    public InvincibilityPotion(MovementBehaviour enemyMovementBehaviour, int duration) {
        super(enemyMovementBehaviour, duration);
    }

    @Override
    public void consumedBy(Player player) {

    }
}
