package dungeonmania.factory.itemfactory;

import java.util.HashMap;
import java.util.Map;

import dungeonmania.entities.DungeonObject;
import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.util.Position;

public class ItemFactory implements DungeonObjectFactory {
    private Map<String, ItemBuilder> ItemBuilders = new HashMap<>();

    public ItemFactory() {
        this.ItemBuilders.put("sword", new SwordBuilder());
        this.ItemBuilders.put("key", new KeyBuilder());
        this.ItemBuilders.put("treasure", new TreasureBuilder());

    }

    @Override
    public DungeonObject create(Position position, String type, String portalColour, int key) {
        ItemBuilder ItemBuilder = this.ItemBuilders.get(type);
        DungeonObject newDungeonObject = ItemBuilder.buildItem(position, type, key);
        return newDungeonObject;
    }
}
