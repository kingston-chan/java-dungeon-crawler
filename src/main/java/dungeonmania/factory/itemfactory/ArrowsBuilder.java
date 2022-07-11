package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Arrows;
import dungeonmania.util.Position;

public class ArrowsBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        Arrows arrows = new Arrows();
        arrows.setPosition(position);
        arrows.setType(type);
        arrows.setUniqueId(UUID.randomUUID().toString());
        return arrows;
    }
}
