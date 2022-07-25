package dungeonmania.factory.itemfactory;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.factory.FactoryHelpers;

public class ItemFactory implements DungeonObjectFactory {
    private Map<String, ItemBuilder> itemBuilders = new HashMap<>();

    public ItemFactory() {
        this.itemBuilders.put("sword", new SwordBuilder());
        this.itemBuilders.put("key", new KeyBuilder());
        this.itemBuilders.put("treasure", new BribableTreasureBuilder());
        this.itemBuilders.put("sun_stone", new SunStoneBuilder());
        this.itemBuilders.put("arrow", new ArrowsBuilder());
        this.itemBuilders.put("bomb", new BombBuilder());
        this.itemBuilders.put("invincibility_potion", new InvincibilityPotionBuilder());
        this.itemBuilders.put("invisibility_potion", new InvisibilityPotionBuilder());
        this.itemBuilders.put("wood", new WoodBuilder());
    }

    @Override
    public void create(JSONObject dungeonObject) {
        ItemBuilder itemBuilder = this.itemBuilders.get(FactoryHelpers.extractType(dungeonObject));
        itemBuilder.buildItem(dungeonObject);
    }
}
