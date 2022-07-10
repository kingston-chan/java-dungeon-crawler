package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.equiments.Bow;
import dungeonmania.util.Position;

public class BowBuilder implements ItemBuilder{

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        Bow bow = new Bow();
        bow.setDurability(dungeon.getConfig("bow_durability"));
        bow.setHostBehaviour(null);
        bow.setPosition(position);
        bow.setType(type);
        bow.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return bow;
    }
}
