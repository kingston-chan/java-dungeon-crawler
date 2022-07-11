package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.Bomb;
import dungeonmania.util.Position;

public class BombBuilder implements ItemBuilder {
    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Bomb bomb = new Bomb();
        bomb.setPosition(position);
        bomb.setType(type);
        bomb.setUniqueId(UUID.randomUUID().toString());
        return bomb;
    }
}
