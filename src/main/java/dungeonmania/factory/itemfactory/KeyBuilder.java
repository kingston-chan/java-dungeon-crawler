package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Key;
import dungeonmania.util.Position;

public class KeyBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        Key key = new Key();
        key.setPosition(position);
        key.setType(type);
        key.setUniqueId(UUID.randomUUID().toString());

        return key;
    }
}
