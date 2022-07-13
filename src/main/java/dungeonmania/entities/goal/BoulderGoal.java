package dungeonmania.entities.goal;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class BoulderGoal implements Goal {

    @Override
    public boolean hasAchieved() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        List<DungeonObject> floorSwitches = dungeon.getDungeonObjects().stream()
                .filter(o -> o instanceof FloorSwitch).collect(Collectors.toList());
        return floorSwitches.stream().allMatch(o -> ((FloorSwitch) o).isActivated()) && floorSwitches.size() > 0;
    }

    @Override
    public String toString() {
        return hasAchieved() ? "" : ":boulders";
    }

}
