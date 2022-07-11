package dungeonmania.entities.goal;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class BoulderGoal implements Goal {
    @Override
    public boolean hasAchieved(StringBuilder allGoals) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Boolean allSwitchActivated = dungeon.getDungeonObjects().stream()
                .filter(dungeonObject -> dungeonObject instanceof FloorSwitch)
                .allMatch(FloorSwitch::isActivated);

        if (allSwitchActivated) {
            return true;
        }

        allGoals.append(":boulders");
        return false;
    }
}
