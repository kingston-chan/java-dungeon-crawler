package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public interface Goal {
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals);
}
