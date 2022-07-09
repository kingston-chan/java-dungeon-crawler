package dungeonmania.entities.actor.player.buildables;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Inventory;

public interface BuildableBlueprint {
    public boolean isBuildable(Inventory inventory);

    public void buildItem(Dungeon dungeon, Inventory inventory);
}
