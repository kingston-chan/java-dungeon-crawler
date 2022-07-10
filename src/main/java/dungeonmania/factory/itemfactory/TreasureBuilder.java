package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Treasure;
import dungeonmania.util.Position;

public class TreasureBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon, int keyNum) {
        Treasure treasure = new Treasure();
        treasure.setHostBehaviour(new ItemHost());
        treasure.setPosition(position);
        treasure.setType(type);
        treasure.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return treasure;
    }

}
