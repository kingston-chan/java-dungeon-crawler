package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class BoulderGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        allGoals.append(":boulders");
        return false;
    }
}
