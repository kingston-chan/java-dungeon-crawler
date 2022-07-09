package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;

public class ExitGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        allGoals.append(":exit");
        for (DungeonObject so : dungeon.getDungeonObjects()) {
            if (so.getType().equals("exit")) {
                return so.getPosition().equals(dungeon.getPlayer().getPosition());
            }
        }
        return false;
    }
}
