package dungeonmania.entities.actor.player.interactables;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.equipment.Equipment;
import dungeonmania.entities.item.equipment.Weapon;
import dungeonmania.util.Position;

public class ZombieSpawnerInteract implements InteractBehaviour {
    @Override
    public boolean interact(Dungeon dungeon, Player player, String interactingWithId) {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
