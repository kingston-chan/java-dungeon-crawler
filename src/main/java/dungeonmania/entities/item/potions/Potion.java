package dungeonmania.entities.item.potions;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Potion extends Item {
    private MovementBehaviour enemyMovementBehaviour;
    private int duration;

    public Potion(MovementBehaviour enemyMovementBehaviour, int duration) {
        this.enemyMovementBehaviour = enemyMovementBehaviour;
        this.duration = duration;
    }

    public MovementBehaviour getEnemyMovementBehaviour() {
        return enemyMovementBehaviour;
    }

    public int getDuration() {
        return duration;
    }

    public abstract void consumedBy(Player player);

    @Override
    public boolean playerUse(Player player) {
        player.usePotion(this);
        player.removeFromInventory(this);
        return true;
    }
}
