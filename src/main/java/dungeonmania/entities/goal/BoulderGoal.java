package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.StaticObject;

public class BoulderGoal implements Goal {
    @Override
    public boolean hasAchieved(Dungeon dungeon, StringBuilder allGoals) {
        // for (StaticObject so : dungeon.getStaticObjects()) {
        // if (so.getType().equals("switch") && !so.isActivated()) {
        // allGoals.append(":boulders");
        // return false;
        // }
        // }
        // return true;

        allGoals.append(":boulders");
        return false;
    }
}
