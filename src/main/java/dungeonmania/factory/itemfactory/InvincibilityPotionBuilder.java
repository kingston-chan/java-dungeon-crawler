package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.potions.InvincibilityPotion;
import dungeonmania.factory.FactoryHelpers;

public class InvincibilityPotionBuilder implements ItemBuilder {

    @Override
    public void buildItem(JSONObject item) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        InvincibilityPotion invincibilityPotion = new InvincibilityPotion(
                dungeon.getIntConfig("invincibility_potion_duration"));
        invincibilityPotion.setPosition(FactoryHelpers.extractPosition(item));
        invincibilityPotion.setType(FactoryHelpers.extractType(item));
        invincibilityPotion.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(invincibilityPotion.getUniqueId(), invincibilityPotion);
    }
}
