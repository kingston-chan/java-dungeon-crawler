package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.potions.InvincibilityPotion;
import dungeonmania.util.Position;

public class InvincibilityPotionBuilder implements ItemBuilder {

    @Override
    public void buildItem(Position position, String type, int keyNum) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        InvincibilityPotion invincibilityPotion = new InvincibilityPotion(
                dungeon.getConfig("invincibility_potion_duration"));
        invincibilityPotion.setPosition(position);
        invincibilityPotion.setType(type);
        invincibilityPotion.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(invincibilityPotion.getUniqueId(), invincibilityPotion);
    }
}
