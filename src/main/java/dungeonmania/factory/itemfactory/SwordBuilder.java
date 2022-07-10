package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.equipment.Sword;
import dungeonmania.util.Position;

public class SwordBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon, int keyNum) {
        Sword sword = new Sword(
                dungeon.getConfig("sword_attack"),
                dungeon.getConfig("sword_durability"));
        sword.setHostBehaviour(null);
        sword.setPosition(position);
        sword.setType(type);
        sword.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return sword;
    }

}
