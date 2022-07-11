package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.collectables.Treasure;

public class TreasureGoal implements Goal {
    @Override
    public boolean hasAchieved(StringBuilder allGoals) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        long treasureCount = player.getInventory().stream().filter(item -> item instanceof Treasure).count();
        if (treasureCount >= dungeon.getConfig("treasure_goal")) {
            return true;
        }
        allGoals.append(":treasure");
        return false;
    }
}
