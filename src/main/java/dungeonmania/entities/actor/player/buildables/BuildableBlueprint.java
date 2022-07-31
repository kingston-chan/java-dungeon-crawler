package dungeonmania.entities.actor.player.buildables;

import dungeonmania.entities.actor.player.Player;

public interface BuildableBlueprint extends java.io.Serializable {
    public boolean canPlayerBuild(Player player);

    public void playerBuild(Player player);
}
