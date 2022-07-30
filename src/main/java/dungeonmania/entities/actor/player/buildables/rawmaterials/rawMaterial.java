package dungeonmania.entities.actor.player.buildables.rawmaterials;

import dungeonmania.entities.actor.player.Player;

public interface rawMaterial extends java.io.Serializable{
    public void playerUseForBuild(Player player);
    public boolean playerHas(Player player);
}
