package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.potions.InvisibilityPotion;
import dungeonmania.util.Position;

public class InvisibilityPotionBuilder implements ItemBuilder {

    @Override
    public void buildItem(Position position, String type, int keyNum) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        InvisibilityPotion invisibilityPotion = new InvisibilityPotion(
                dungeon.getConfig("invisibility_potion_duration"));
        invisibilityPotion.setPosition(position);
        invisibilityPotion.setType(type);
        invisibilityPotion.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(invisibilityPotion.getUniqueId(), invisibilityPotion);
    }
}
