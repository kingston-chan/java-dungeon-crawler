package dungeonmania.entities.actor.player.buildables;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equiments.Shield;

public class ShieldBlueprint implements BuildableBlueprint {
    private static final int NUM_WOOD = 2;
    private static final int NUM_TREASURES = 1;
    private static final String ITEM_TYPE = "shield";

    private Item createNewShield(Dungeon dungeon) {
        Shield shield = new Shield();
        shield.setDurability(dungeon.getConfig("shield_durability"));
        shield.setPosition(null);
        shield.setType(ITEM_TYPE);
        shield.setUniqueId(UUID.randomUUID().toString());

        return shield;
    }

    private void buildWithTreasure(Player player, Item shield) {
        player.addToInventory(shield);
        ItemGetterHelpers.removeTreasuresFromInventory(NUM_TREASURES, player);
        ItemGetterHelpers.removeWoodFromInventory(NUM_WOOD, player);
    }

    private void buildWithKey(Player player, Item shield) {
        player.addToInventory(shield);
        ItemGetterHelpers.removeWoodFromInventory(NUM_WOOD, player);
        player.removeFromInventory(player.getKey());
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        return (player.getKey() != null || ItemGetterHelpers.getNumTreasure(player) >= NUM_TREASURES)
                && ItemGetterHelpers.getNumWood(player) >= NUM_WOOD;
    }

    @Override
    public void playerBuild(Dungeon dungeon, Player player) {
        if (ItemGetterHelpers.getNumTreasure(player) >= NUM_TREASURES) {
            buildWithTreasure(player, createNewShield(dungeon));
        } else {
            buildWithKey(player, createNewShield(dungeon));
        }
    }
}
