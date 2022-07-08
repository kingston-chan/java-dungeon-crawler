package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public class TreasureGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        allGoals.append(":treasure");
        return false;
    }
}
