package dungeonmania.entities.actor.player.buildables.sceptrepart;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.buildables.BuildableBlueprint;
import dungeonmania.entities.actor.player.buildables.sceptrepart.rawmaterials.SunstonePart;
import dungeonmania.entities.actor.player.buildables.sceptrepart.rawmaterials.WoodPart;
import dungeonmania.entities.actor.player.buildables.sceptrepart.rawmaterials.rawMaterial;

public class SceptrePartE implements BuildableBlueprint{
    List<rawMaterial> rawMaterialsRequired = new ArrayList<>();
    List<rawMaterial> availableMaterials = new ArrayList<>();

    public SceptrePartE() {
        rawMaterialsRequired.add(new SunstonePart(2));
        rawMaterialsRequired.add(new WoodPart(1));
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
