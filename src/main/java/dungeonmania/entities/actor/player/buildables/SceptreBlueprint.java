package dungeonmania.entities.actor.player.buildables;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.buildables.sceptrepart.SceptrePartA;
import dungeonmania.entities.actor.player.buildables.sceptrepart.SceptrePartB;
import dungeonmania.entities.actor.player.buildables.sceptrepart.SceptrePartC;
import dungeonmania.entities.actor.player.buildables.sceptrepart.SceptrePartD;
import dungeonmania.entities.actor.player.buildables.sceptrepart.SceptrePartE;
import dungeonmania.entities.actor.player.buildables.sceptrepart.SceptrePartF;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.Sceptre;

public class SceptreBlueprint implements BuildableBlueprint {
    private List<BuildableBlueprint> parts = new ArrayList<>();
    private List<BuildableBlueprint> AvailableForBuild = new ArrayList<>();
    private static final String ITEM_TYPE = "sceptre";

    public SceptreBlueprint() {
        parts.add(new SceptrePartA());
        parts.add(new SceptrePartB());
        parts.add(new SceptrePartC());
        parts.add(new SceptrePartD());
        parts.add(new SceptrePartE());
        parts.add(new SceptrePartF());
    }

    private Item createNewSceptre() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Sceptre sceptre = new Sceptre(
                dungeon.getIntConfig("mind_control_duration")
                ,3);
        sceptre.setPosition(null);
        sceptre.setType(ITEM_TYPE);
        sceptre.setUniqueId(UUID.randomUUID().toString());
        return sceptre;
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        parts.stream().filter(part -> part.canPlayerBuild(player))
                    .forEach(part -> AvailableForBuild.add(part));
        return !AvailableForBuild.isEmpty();
    }

    @Override
    public void playerBuild(Player player) {
        AvailableForBuild.get(0).playerBuild(player);
        player.addToInventory(createNewSceptre());
    }
}
