package dungeonmania.entities.actor.player.buildables.bowparts;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.buildables.BuildableBlueprint;
import dungeonmania.entities.actor.player.buildables.rawmaterials.ArrowPart;
import dungeonmania.entities.actor.player.buildables.rawmaterials.WoodPart;
import dungeonmania.entities.actor.player.buildables.rawmaterials.rawMaterial;

public class BowPartA implements BuildableBlueprint{
    List<rawMaterial> rawMaterialsRequired = new ArrayList<>();
    List<rawMaterial> availableMaterials = new ArrayList<>();

    public BowPartA() {
        rawMaterialsRequired.add(new WoodPart(1));
        rawMaterialsRequired.add(new ArrowPart(3));
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        availableMaterials.clear();
        rawMaterialsRequired.stream()
            .filter(item -> item.playerHas(player))
            .forEach(item -> availableMaterials.add(item));

        return availableMaterials.size() == rawMaterialsRequired.size();
    }

    @Override
    public void playerBuild(Player player) {
        availableMaterials.stream()
            .forEach(item -> item.playerUseForBuild(player));
    }
}
