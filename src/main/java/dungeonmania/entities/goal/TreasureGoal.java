package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;

public class TreasureGoal implements Goal {

    @Override
    public boolean hasAchieved() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        return ItemGetterHelpers.getNumTreasure(player) >= dungeon.getIntConfig("treasure_goal");
    }

    @Override
    public String toString() {
        return this.hasAchieved() ? "" : ":treasure";
    }
}
