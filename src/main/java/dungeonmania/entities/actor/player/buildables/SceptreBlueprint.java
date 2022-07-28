package dungeonmania.entities.actor.player.buildables;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.Sceptre;

public class SceptreBlueprint implements BuildableBlueprint {
    private static final int NUM_WOOD = 1;
    private static final int NUM_ARROW = 2;

    private static final int NUM_TREASURES = 1;

    private static final int NUM_SUNSTONE = 1;
    private static final String ITEM_TYPE = "sceptre";

    private Item createNewSceptre() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Sceptre sceptre = new Sceptre(
                dungeon.getIntConfig("mind_control_duration")
                ,3);
        sceptre.setPosition(null);
        sceptre.setType(ITEM_TYPE);
        sceptre.setUniqueId(UUID.randomUUID().toString());
        return sceptre;
    }

    private void build_with_key_Wood(Player player, Item sceptre){
        player.addToInventory(sceptre);
        ItemGetterHelpers.removeSunStoneFromInventory(NUM_SUNSTONE, player);
        player.removeFromInventory(player.getKey());
        ItemGetterHelpers.removeWoodFromInventory(NUM_WOOD, player);
    }

    private void build_with_treasure_Wood(Player player, Item sceptre){
        player.addToInventory(sceptre);
        ItemGetterHelpers.removeSunStoneFromInventory(NUM_SUNSTONE, player);
        ItemGetterHelpers.removeTreasuresFromInventory(NUM_TREASURES, player);
        ItemGetterHelpers.removeWoodFromInventory(NUM_WOOD, player);
    }

    private void build_with_key_arrows(Player player, Item sceptre){
        player.addToInventory(sceptre);
        ItemGetterHelpers.removeSunStoneFromInventory(NUM_SUNSTONE, player);
        player.removeFromInventory(player.getKey());
        ItemGetterHelpers.removeArrowsFromInventory(NUM_ARROW, player);
    }

    private void build_with_treasure_arrows(Player player, Item sceptre){
        player.addToInventory(sceptre);
        ItemGetterHelpers.removeSunStoneFromInventory(NUM_SUNSTONE, player);
        ItemGetterHelpers.removeTreasuresFromInventory(NUM_TREASURES, player);
        ItemGetterHelpers.removeArrowsFromInventory(NUM_ARROW, player);
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        return ((player.getKey() != null || ItemGetterHelpers.getNumBribableTreasure(player) >= NUM_TREASURES)
        || ItemGetterHelpers.getNumSunStone(player) >= 2)
        && (ItemGetterHelpers.getNumWood(player) >= NUM_WOOD || ItemGetterHelpers.getNumArrows(player) >= NUM_ARROW)
        && (ItemGetterHelpers.getNumSunStone(player) >= NUM_SUNSTONE);
    }

    @Override
    public void playerBuild(Player player) {
        boolean check_key_wood = (player.getKey() != null
                                && ItemGetterHelpers.getNumWood(player) >= NUM_WOOD);
        boolean check_treasure_wood = ((ItemGetterHelpers.getNumTreasure(player) >= NUM_TREASURES)
                                && (ItemGetterHelpers.getNumWood(player) >= NUM_WOOD));
        boolean check_key_arrows = (player.getKey() != null
                                    && ItemGetterHelpers.getNumArrows(player) >= NUM_ARROW);
        boolean check_treasure_arrows = (ItemGetterHelpers.getNumTreasure(player) >= NUM_TREASURES
                                        && ItemGetterHelpers.getNumArrows(player) >= NUM_ARROW);

        if (check_key_wood) {
            build_with_key_Wood(player, createNewSceptre());
        } else if (check_treasure_wood) {
            build_with_treasure_Wood(player, createNewSceptre());
        } else if (check_key_arrows) {
            build_with_key_arrows(player, createNewSceptre());
        } else if (check_treasure_arrows) {
            build_with_treasure_arrows(player, createNewSceptre());
        }
    }
}
