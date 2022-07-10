package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Arrows;
import dungeonmania.util.Position;

public class ArrowsBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon, int keyNum) {
        Arrows arrows = new Arrows();
        arrows.setHostBehaviour(null);
        arrows.setPosition(position);
        arrows.setType(type);
        arrows.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return arrows;
    }
}
