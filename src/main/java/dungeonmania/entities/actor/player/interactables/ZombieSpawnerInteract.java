package dungeonmania.entities.actor.player.interactables;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.equiments.Equipment;
import dungeonmania.util.Position;

public class ZombieSpawnerInteract implements InteractBehaviour {
    @Override
    public boolean interact(Dungeon dungeon, Player player, String interactingWithId) {
        DungeonObject zombieSpawner = dungeon.getDungeonObject(interactingWithId);
        if (!Position.isAdjacent(player.getPosition(), zombieSpawner.getPosition())) {
            return false;
        }

        List<Equipment> allEquipment = player.getInventory().stream()
                .filter(item -> item instanceof Equipment)
                .map(e -> (Equipment) e).collect(Collectors.toList());

        // for (Equipment e : allEquipment) {
        // if (e.provideAttack(player, null)) {
        // dungeon.removeDungeonObject(interactingWithId);
        // return true;
        // }
        // }

        return false;
    }
}
