package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.item.Key;
import dungeonmania.factory.FactoryHelpers;

public class KeyBuilder implements ItemBuilder {
    @Override
    public void buildItem(JSONObject item) {
        Key key = new Key(FactoryHelpers.extractKey(item));
        key.setPosition(FactoryHelpers.extractPosition(item));
        key.setType(FactoryHelpers.extractType(item));
        key.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(key.getUniqueId(), key);
    }
}
