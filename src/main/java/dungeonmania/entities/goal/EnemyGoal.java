package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class EnemyGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        allGoals.append(":enemies");
        return false;
    }
}
