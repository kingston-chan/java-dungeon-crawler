package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.floorswitch.DeactivatedState;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.floorswitch.LogicDeactivatedState;
import dungeonmania.entities.staticobject.logicentities.LogicFloorSwitch;
import dungeonmania.factory.FactoryHelpers;
import dungeonmania.factory.LogicFactory;

public class FloorSwitchBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        FloorSwitch floorSwitch;
        try {
            String logicType = FactoryHelpers.extractLogic(staticObject);
            floorSwitch = new LogicFloorSwitch(LogicFactory.getLogicType(logicType));
            floorSwitch.setDeactivatedState(new LogicDeactivatedState(floorSwitch));
        } catch (JSONException e) {
            floorSwitch = new FloorSwitch();
            floorSwitch.setDeactivatedState(new DeactivatedState(floorSwitch));
        }
        floorSwitch.setPosition(FactoryHelpers.extractPosition(staticObject));
        floorSwitch.setUniqueId(UUID.randomUUID().toString());
        floorSwitch.setType(FactoryHelpers.extractType(staticObject));
        DungeonManiaController.getDungeon().addDungeonObject(floorSwitch.getUniqueId(), floorSwitch);
    }

}
