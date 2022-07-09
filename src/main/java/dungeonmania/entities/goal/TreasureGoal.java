package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.Item;

public class TreasureGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        int treasureCount = 0;
        for (Item i : dungeon.getPlayer().getInventory()) {
            if (i.getType().equals("treasure"))
                treasureCount++;
        }
        if (treasureCount >= dungeon.getConfig("treasure_goal")) {
            return true;
        }
        allGoals.append(":treasure");
        return false;
    }
}
