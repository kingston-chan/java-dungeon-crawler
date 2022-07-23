package dungeonmania.entities.actor.player.helpers;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.collectables.Arrows;
import dungeonmania.entities.item.collectables.Wood;
import dungeonmania.entities.item.collectables.treasure.BribableTreasure;
import dungeonmania.entities.item.collectables.treasure.SunStone;
import dungeonmania.entities.item.collectables.treasure.Treasure;
import dungeonmania.entities.item.equipment.Sceptre;
import dungeonmania.entities.item.equipment.Sword;

public class ItemGetterHelpers {
    public static long getNumTreasure(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Treasure).count();
    }

    public static long getNumBribableTreasure(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Treasure)
                .filter(treasure -> ((Treasure) treasure).isBribableCurrency()).count();
    }

    public static long getNumWood(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Wood).count();
    }

    public static long getNumArrows(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Arrows).count();
    }

    public static long getNumSword(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Sword).count();
    }

    public static long getNumSunStone(Player player) {
        // waiting for sunstone
        return 0;
    }

    private static Wood getSingleWood(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Wood)
                .map(wood -> (Wood) wood)
                .findFirst().get();
    }

    private static Treasure getSingleBribableTreasure(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Treasure)
                .filter(treasure -> ((Treasure) treasure).isBribableCurrency())
                .map(treasure -> (Treasure) treasure)
                .findFirst().get();
    }

    private static Treasure getSingleSunStone(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Treasure)
                .filter(treasure -> !((Treasure) treasure).isBribableCurrency())
                .map(treasure -> (Treasure) treasure)
                .findFirst().get();
    }

    public static long getNumSunStone(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof SunStone).count();
    }

    private static Arrows getSingleArrow(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Arrows)
                .map(arrow -> (Arrows) arrow)
                .findFirst().get();
    }

    private static Sword getSingleSword(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Sword)
                .map(sword -> (Sword) sword)
                .findFirst().get();
    }
    // this should be the Sunstone one
    private static Item getSingleSunstone(Player player) {
        // return player.getInventory().stream()
        //         .filter(item -> item instanceof SunStone)
        //         .map(sunstone -> (SunStone) sunstone)
        //         .findFirst.get();
        return null;
    }

    public static void removeTreasuresFromInventory(int numTreasures, Player player) {
        for (int i = 0; i < numTreasures; i++) {
            player.removeFromInventory(getSingleBribableTreasure(player));
        }
    }

    public static void removeSunStoneFromInventory(int numSunStones, Player player) {
        for (int i = 0; i < numSunStones; i++) {
            player.removeFromInventory(getSingleSunStone(player));
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

    public static void removeSwordFromInventory(int numSwords, Player player) {
        for (int i = 0; i < numSwords; i++) {
            player.removeFromInventory(getSingleSword(player));
        }
    }

    public static void removeSunStoneFromInventory(int numSunstone, Player player) {
        for (int i = 0; i < numSunstone; i++) {
            player.removeFromInventory(getSingleSunstone(player));
        }
    }

    public static Sceptre getSceptreFromInventory(Player player) {
        return player.getInventory().stream()
                .filter(item -> item instanceof Sceptre)
                .map(item -> (Sceptre) item)
                .findFirst().get();
    }
}
