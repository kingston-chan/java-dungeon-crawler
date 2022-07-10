package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.Bomb;
import dungeonmania.util.Position;

public class BombBuilder implements ItemBuilder {
    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon, int keyNum) {
        Bomb bomb = new Bomb();
        bomb.setHostBehaviour(null);
        bomb.setPosition(position);
        bomb.setType(type);
        bomb.setUniqueId(UUID.randomUUID().toString());
        bomb.setHostBehaviour(new ItemHost());
        return bomb;
    }
}
