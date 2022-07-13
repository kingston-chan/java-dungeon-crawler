package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.util.Position;

public class FloorSwitchBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(Position position, String type, String portalColour, int key) {
        FloorSwitch floorSwitch = new FloorSwitch();
        floorSwitch.setPosition(position);
        floorSwitch.setUniqueId(UUID.randomUUID().toString());
        floorSwitch.setType(type);
        DungeonManiaController.getDungeon().addDungeonObject(floorSwitch.getUniqueId(), floorSwitch);
    }

}
