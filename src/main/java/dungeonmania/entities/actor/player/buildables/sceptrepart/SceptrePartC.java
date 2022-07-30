package dungeonmania.entities.actor.player.buildables.sceptrepart;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.buildables.BuildableBlueprint;
import dungeonmania.entities.actor.player.buildables.sceptrepart.rawmaterials.ArrowPart;
import dungeonmania.entities.actor.player.buildables.sceptrepart.rawmaterials.KeyPart;
import dungeonmania.entities.actor.player.buildables.sceptrepart.rawmaterials.SunstonePart;
import dungeonmania.entities.actor.player.buildables.sceptrepart.rawmaterials.rawMaterial;

public class SceptrePartC implements BuildableBlueprint{
    List<rawMaterial> rawMaterialsRequired = new ArrayList<>();
    List<rawMaterial> availableMaterials = new ArrayList<>();

    public SceptrePartC() {
        rawMaterialsRequired.add(new SunstonePart(1));
        rawMaterialsRequired.add(new KeyPart(1));
        rawMaterialsRequired.add(new ArrowPart(2));
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
