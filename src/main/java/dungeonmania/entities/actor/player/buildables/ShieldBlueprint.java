package dungeonmania.entities.actor.player.buildables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Inventory;
import dungeonmania.entities.item.Item;
import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.factory.FactoryChooser;

public class ShieldBlueprint implements BuildableBlueprint {
    private static final int NUM_WOOD = 2;
    private static final int NUM_TREASURES = 1;
    private static final String ITEM_TYPE = "shield";

    @Override
    public boolean isBuildable(Inventory inventory) {
        return (inventory.getKey() != null || inventory.getNumTreasures() >= NUM_TREASURES)
                && inventory.getNumWood() >= NUM_WOOD;
    }

    @Override
    public void buildItem(Dungeon dungeon, Inventory inventory) {
        FactoryChooser fc = new FactoryChooser();
        DungeonObjectFactory dof = fc.getFactory(ITEM_TYPE);
        DungeonObject d = dof.create(null, ITEM_TYPE, dungeon, "", -1);
        Item shield = dungeon.getItemInDungeon(d.getUniqueId());
        if (inventory.getNumTreasures() >= NUM_TREASURES) {
            inventory.addItem(shield);
            inventory.removeTreasures(NUM_TREASURES);
            inventory.removeWoods(NUM_WOOD);
            dungeon.removeItemFromDungeon(shield);
        } else {
            inventory.addItem(shield);
            inventory.removeWoods(NUM_WOOD);
            inventory.addKey(null);
            dungeon.removeItemFromDungeon(shield);
        }
    }
}
