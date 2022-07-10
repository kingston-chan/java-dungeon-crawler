package dungeonmania.entities.item;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;

public abstract class Item extends DungeonObject {

<<<<<<< HEAD
    public void collectedBy(Player player) {
        player.addToInventory(this);
    }

    public abstract boolean playerUse(Player player, Dungeon dungeon);
=======
    public abstract boolean playerUse(Player player);
    public abstract boolean provideAttack(Player player);
    public abstract boolean provideDefense(Player player);
>>>>>>> dde6ed2eff588145557401c74e82e0ecae700137

    @Override
    public boolean isInteractable() {
        return false;
    }
}