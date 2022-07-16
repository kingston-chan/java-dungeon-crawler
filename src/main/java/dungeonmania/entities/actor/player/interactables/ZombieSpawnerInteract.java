package dungeonmania.entities.actor.player.interactables;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.equipment.Weapon;
import dungeonmania.util.Position;

public class ZombieSpawnerInteract implements InteractBehaviour {
    @Override
    public boolean interact(Player player, String interactingWithId) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        DungeonObject zombieSpawner = dungeon.getDungeonObject(interactingWithId);

        if (!Position.isAdjacent(player.getPosition(), zombieSpawner.getPosition())) {
            return false;
        }

        try {
            Weapon weapon = player.getInventory().stream()
                    .filter(item -> item instanceof Weapon)
                    .map(e -> (Weapon) e).findFirst().get();
            weapon.playerEquip(player);
            dungeon.removeDungeonObject(interactingWithId);
            player.defeatedEnemy();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
