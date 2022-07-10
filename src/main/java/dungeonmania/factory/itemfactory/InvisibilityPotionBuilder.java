package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.Potion;
import dungeonmania.util.Position;

public class InvisibilityPotionBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon, int keyNum) {
        Potion invisibilityPotion = new Potion(
                new InvisiblePlayerHost(),
                new InvisiblePlayerEnemyHost(),
                new MoveRandomly(),
                dungeon.getConfig("invisibility_potion_duration"));
        invisibilityPotion.setHostBehaviour(new ItemHost());
        invisibilityPotion.setPosition(position);
        invisibilityPotion.setType(type);
        invisibilityPotion.setUniqueId(UUID.randomUUID().toString());

        return invisibilityPotion;
    }
}
