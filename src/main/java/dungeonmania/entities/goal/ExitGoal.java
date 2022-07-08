package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class ExitGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        allGoals.append(":exit");
        return false;
    }
}
