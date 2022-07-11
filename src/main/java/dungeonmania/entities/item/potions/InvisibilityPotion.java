package dungeonmania.entities.item.potions;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;

public class InvisibilityPotion extends Potion {

    public InvisibilityPotion(MovementBehaviour enemyMovementBehaviour, int duration) {
        super(enemyMovementBehaviour, duration);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void consumedBy(Player player) {
        // TODO Auto-generated method stub

    }

}
