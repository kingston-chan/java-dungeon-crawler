package dungeonmania.entities.actor.player.buildables;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equiments.Bow;

public class BowBlueprint implements BuildableBlueprint {
    private static final int NUM_ARROWS = 3;
    private static final int NUM_WOOD = 1;
    private static final String ITEM_TYPE = "bow";

    private Item createNewBow(Dungeon dungeon) {
        Bow bow = new Bow();
        bow.setUniqueId(UUID.randomUUID().toString());
        bow.setDurability(dungeon.getConfig("bow_durability"));
        bow.setHostBehaviour(null);
        bow.setPosition(null);
        bow.setType(ITEM_TYPE);
        return bow;
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        return ItemGetterHelpers.getNumArrows(player) >= NUM_ARROWS && ItemGetterHelpers.getNumWood(player) >= NUM_WOOD;
    }

    @Override
    public void playerBuild(Dungeon dungeon, Player player) {
        player.addToInventory(createNewBow(dungeon));
        ItemGetterHelpers.removeArrowsFromInventory(NUM_ARROWS, player);
        ItemGetterHelpers.removeWoodFromInventory(NUM_WOOD, player);
    }
}
