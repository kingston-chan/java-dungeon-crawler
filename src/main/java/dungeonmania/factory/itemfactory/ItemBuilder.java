package dungeonmania.factory.itemfactory;

import dungeonmania.entities.DungeonObject;
import dungeonmania.util.Position;

public interface ItemBuilder {
    public DungeonObject buildItem(Position position, String type, int keyNum);
}
