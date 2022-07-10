package dungeonmania.entities.item.collectables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Collectable extends Item {

    @Override
<<<<<<< HEAD
    public boolean playerUse(Player player, Dungeon dungeon) {
=======
    public boolean playerUse(Player player) {
        return false;
    }

    @Override
    public boolean provideAttack(Player player) {
        return false;
    }

    @Override
    public boolean provideDefense(Player player) {
>>>>>>> dde6ed2eff588145557401c74e82e0ecae700137
        return false;
    }
}
