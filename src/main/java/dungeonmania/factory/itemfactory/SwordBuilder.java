package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.equiments.Sword;
import dungeonmania.util.Position;

public class SwordBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Sword sword = new Sword();
        sword.setDurability(dungeon.getConfig("sword_durability"));
        sword.setPosition(position);
        sword.setType(type);
        sword.setUniqueId(UUID.randomUUID().toString());

        return sword;
    }

}