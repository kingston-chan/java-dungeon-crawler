package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.potions.InvisibilityPotion;
import dungeonmania.factory.FactoryHelpers;

public class InvisibilityPotionBuilder implements ItemBuilder {
    @Override
    public void buildItem(JSONObject item) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        InvisibilityPotion invisibilityPotion = new InvisibilityPotion(
                dungeon.getIntConfig("invisibility_potion_duration"));
        invisibilityPotion.setPosition(FactoryHelpers.extractPosition(item));
        invisibilityPotion.setType(FactoryHelpers.extractType(item));
        invisibilityPotion.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(invisibilityPotion.getUniqueId(), invisibilityPotion);
    }
}
