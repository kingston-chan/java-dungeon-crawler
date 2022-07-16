package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;

public class EnemyGoal implements Goal {

    @Override
    public boolean hasAchieved() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        return player.getEnemiesDefeated() >= dungeon.getConfig("enemy_goal");
    }

    @Override
    public String toString() {
        return this.hasAchieved() ? "" : ":enemies";
    }
}
