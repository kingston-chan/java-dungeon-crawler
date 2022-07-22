package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.factory.FactoryHelpers;

public class FloorSwitchBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        FloorSwitch floorSwitch = new FloorSwitch();
        floorSwitch.setPosition(FactoryHelpers.extractPosition(staticObject));
        floorSwitch.setUniqueId(UUID.randomUUID().toString());
        floorSwitch.setType(FactoryHelpers.extractType(staticObject));
        DungeonManiaController.getDungeon().addDungeonObject(floorSwitch.getUniqueId(), floorSwitch);
    }

}
