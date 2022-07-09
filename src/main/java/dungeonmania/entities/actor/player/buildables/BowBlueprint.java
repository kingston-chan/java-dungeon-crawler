package dungeonmania.entities.actor.player.buildables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Inventory;
import dungeonmania.entities.item.Item;
import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.factory.FactoryChooser;

public class BowBlueprint implements BuildableBlueprint {
    private static final int NUM_ARROWS = 3;
    private static final int NUM_WOOD = 1;
    private static final String ITEM_TYPE = "bow";

    @Override
    public boolean isBuildable(Inventory inventory) {
        return inventory.getNumArrows() >= NUM_ARROWS && inventory.getNumWood() >= NUM_WOOD;
    }

    @Override
    public void buildItem(Dungeon dungeon, Inventory inventory) {
        FactoryChooser fc = new FactoryChooser();
        DungeonObjectFactory dof = fc.getFactory(ITEM_TYPE);
        DungeonObject d = dof.create(null, ITEM_TYPE, dungeon, "", -1);
        Item bow = dungeon.getItemInDungeon(d.getUniqueId());
        inventory.addItem(bow);
        inventory.removeArrows(NUM_ARROWS);
        inventory.removeWoods(NUM_WOOD);
        dungeon.removeItemFromDungeon(bow);
    }
}
