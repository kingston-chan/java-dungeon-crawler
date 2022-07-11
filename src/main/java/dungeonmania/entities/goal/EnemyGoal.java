package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;

public class EnemyGoal implements Goal {
    @Override
    public boolean hasAchieved(StringBuilder allGoals) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        if (player.getEnemiesDefeated() >= dungeon.getConfig("enemy_goal")) {
            return true;
        }
        allGoals.append(":enemies");
        return false;
    }
}
