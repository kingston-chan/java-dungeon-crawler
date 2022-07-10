package dungeonmania.entities.item.collectables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;

public abstract class Collectable extends Item {

    @Override
    public boolean playerUse(Player player, Dungeon dungeon) {
        return false;
    }
}
