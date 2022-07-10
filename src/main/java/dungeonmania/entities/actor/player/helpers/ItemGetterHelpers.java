package dungeonmania.entities.actor.player.helpers;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.collectables.Arrows;
import dungeonmania.entities.item.collectables.Treasure;
import dungeonmania.entities.item.collectables.Wood;

public class ItemGetterHelpers {
    public static long getNumTreasure(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Treasure).count();
    }

    public static long getNumWood(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Wood).count();
    }

    public static long getNumArrows(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Arrows).count();
    }

    private static Wood getSingleWood(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Wood)
                .map(wood -> (Wood) wood)
                .findFirst().get();
    }

    private static Treasure getSingleTreasure(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Treasure)
                .map(treasure -> (Treasure) treasure)
                .findFirst().get();
    }

    private static Arrows getSingleArrow(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Arrows)
                .map(arrow -> (Arrows) arrow)
                .findFirst().get();
    }

    public static void removeTreasuresFromInventory(int numTreasures, Player player) {
        for (int i = 0; i < numTreasures; i++) {
            player.removeFromInventory(getSingleTreasure(player));
        }
    }

    public static void removeWoodFromInventory(int numWood, Player player) {
        for (int i = 0; i < numWood; i++) {
            player.removeFromInventory(getSingleWood(player));
        }
    }

    public static void removeArrowsFromInventory(int numArrows, Player player) {
        for (int i = 0; i < numArrows; i++) {
            player.removeFromInventory(getSingleArrow(player));
        }
    }
}
