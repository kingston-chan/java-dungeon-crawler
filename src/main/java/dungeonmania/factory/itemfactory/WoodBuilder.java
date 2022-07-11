package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Wood;
import dungeonmania.util.Position;

public class WoodBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        Wood wood = new Wood();
        wood.setPosition(position);
        wood.setType(type);
        wood.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return wood;
    }
}