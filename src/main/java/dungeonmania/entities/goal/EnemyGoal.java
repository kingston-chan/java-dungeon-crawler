package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class EnemyGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        if (dungeon.getPlayer().getEnemiesDefeated() >= dungeon.getConfig("enemy_goal")) {
            return true;
        }
        allGoals.append(":enemies");
        return false;
    }
}
