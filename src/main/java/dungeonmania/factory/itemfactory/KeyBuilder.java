package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.Key;
import dungeonmania.util.Position;

public class KeyBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        Key key = new Key(keyNum);
        key.setPosition(position);
        key.setType(type);
        key.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(key.getUniqueId(), key);
        return key;
    }
}
