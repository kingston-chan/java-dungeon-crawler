package dungeonmania.entities.item.potions;

import dungeonmania.behaviours.automatedmovement.AutomatedMovementBehaviour;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Potion extends Item {
    AutomatedMovementBehaviour behaviour;

    @Override
    public boolean playerUse(Player player) {
        player.addPotion(this);
        return true;
    }

    @Override
    public boolean provideAttack(Player player) {
        return false;
    }

    @Override
    public boolean provideDefense(Player player) {
        return false;
    }

    public AutomatedMovementBehaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(AutomatedMovementBehaviour behaviour) {
        this.behaviour = behaviour;
    }

}
