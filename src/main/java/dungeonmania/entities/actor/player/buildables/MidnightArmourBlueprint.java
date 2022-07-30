package dungeonmania.entities.actor.player.buildables;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.buildables.MidnightArmourParts.MDArmourPartA;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equipment.MidnightArmor;

public class MidnightArmourBlueprint implements BuildableBlueprint {
    private List<BuildableBlueprint> parts = new ArrayList<>();
    private List<BuildableBlueprint> AvailableForBuild = new ArrayList<>();
    private static final String ITEM_TYPE = "midnight_armour";

    public MidnightArmourBlueprint() {
        parts.add(new MDArmourPartA());
    }

    private Item CreateNewMidnightArmour() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        MidnightArmor midnightArmor = new MidnightArmor(
            dungeon.getIntConfig("midnight_armour_attack"),
            dungeon.getIntConfig("midnight_armour_defence"),
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
        parts.stream().filter(part -> part.canPlayerBuild(player))
                    .forEach(part -> AvailableForBuild.add(part));
        return !AvailableForBuild.isEmpty() & !check_zombies_existence();
    }

    @Override
    public void playerBuild(Player player) {
        AvailableForBuild.get(0).playerBuild(player);
        player.addToInventory(CreateNewMidnightArmour());
    }
}
