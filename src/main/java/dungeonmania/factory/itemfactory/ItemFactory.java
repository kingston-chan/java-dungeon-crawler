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
        this.ItemBuilders.put("arrow", new ArrowsBuilder());
        this.ItemBuilders.put("bomb", new BombBuilder());
        this.ItemBuilders.put("invincibility_potion", new InvincibilityPotionBuilder());
        this.ItemBuilders.put("invisibility_potion", new InvisibilityPotionBuilder());
        this.ItemBuilders.put("wood", new WoodBuilder());
    }

    @Override
    public void create(Position position, String type, String portalColour, int key) {
        ItemBuilder itemBuilder = this.ItemBuilders.get(type);
        itemBuilder.buildItem(position, type, key);
    }
}
