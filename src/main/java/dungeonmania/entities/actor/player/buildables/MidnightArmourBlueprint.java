package dungeonmania.entities.actor.player.buildables;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equipment.MidnightArmor;

public class MidnightArmourBlueprint implements BuildableBlueprint{
    private static final int NUM_SWORD = 1;
    private static final int NUM_SUNSTONE = 1;
    private static final String ITEM_TYPE = "midnight_armour";

    private Item CreateNewMidnightArmour() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        MidnightArmor midnightArmor = new MidnightArmor(
            dungeon.getConfig("midnight_armour_attack"),
            dungeon.getConfig("midnight_armour_defence"),
            -1);
            midnightArmor.setPosition(null);
            midnightArmor.setType(ITEM_TYPE);
            midnightArmor.setUniqueId(UUID.randomUUID().toString());

        return midnightArmor;
    }

    private boolean check_zombies_existence() {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        return dungeon.getDungeonObjects()
                        .stream()
                        .anyMatch(entity -> entity instanceof ZombieToast);
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        return (ItemGetterHelpers.getNumArrows(player) >= NUM_SWORD)
        && (ItemGetterHelpers.getNumSunStone(player) >= NUM_SUNSTONE)
        && (!check_zombies_existence());
    }

    @Override
    public void playerBuild(Player player) {
        player.addToInventory(CreateNewMidnightArmour());
        ItemGetterHelpers.removeSwordFromInventory(NUM_SWORD, player);
        ItemGetterHelpers.removeSunStoneFromInventory(NUM_SUNSTONE, player);
    }
}
