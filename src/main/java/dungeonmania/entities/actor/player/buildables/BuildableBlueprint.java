package dungeonmania.entities.actor.player.buildables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;

public interface BuildableBlueprint {
    public boolean canPlayerBuild(Player player);

    public void playerBuild(Player player);
}
