package dungeonmania.entities.actor.player.interactables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.Position;

public class ZombieSpawnerInteract implements InteractBehaviour {
    @Override
    public boolean interact(Dungeon dungeon, Player player, String interactingWithId) {
        StaticObject zombieSpawner = dungeon.getStaticObject(interactingWithId);
        if (!Position.isAdjacent(player.getPosition(), zombieSpawner.getPosition())) {
            return false;
        }

        // for (Item i : player.getInventory()) {
        // if (i.provideAttack(player, null)) {
        // dungeon.removeFromStaticObjects(zombieSpawner);
        // return true;
        // }
        // }

        return false;
    }
}
