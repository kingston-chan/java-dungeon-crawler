package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.equipment.Sword;
import dungeonmania.util.Position;

public class SwordBuilder implements ItemBuilder {

    @Override
    public void buildItem(Position position, String type, int keyNum) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Sword sword = new Sword(dungeon.getConfig("sword_attack"), dungeon.getConfig("sword_durability"));
        sword.setPosition(position);
        sword.setType(type);
        sword.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(sword.getUniqueId(), sword);
    }

}
