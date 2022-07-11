package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Treasure;
import dungeonmania.util.Position;

public class TreasureBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        Treasure treasure = new Treasure();
        treasure.setPosition(position);
        treasure.setType(type);
        treasure.setUniqueId(UUID.randomUUID().toString());

        return treasure;
    }
}