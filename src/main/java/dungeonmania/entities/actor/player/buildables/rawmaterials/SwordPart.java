package dungeonmania.entities.actor.player.buildables.rawmaterials;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;

public class SwordPart implements rawMaterial{
    private int numRequired;

    public SwordPart(int numRequired) {
        this.numRequired = numRequired;
    }

    @Override
    public void playerUseForBuild(Player player) {
        ItemGetterHelpers.removeSwordFromInventory(numRequired, player);
    }

    @Override
    public boolean playerHas(Player player) {
        return ItemGetterHelpers.getNumSword(player) >= numRequired;
    }

}
