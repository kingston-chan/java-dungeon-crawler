package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Key;
import dungeonmania.util.Position;

public class KeyBuilder implements ItemBuilder{

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        Key key = new Key();
        key.setHostBehaviour(null);
        key.setPosition(position);
        key.setType(type);
        key.setUniqueId(UUID.randomUUID().toString());

        return key;
    }
}
