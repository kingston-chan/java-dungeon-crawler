package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.logicentities.SwitchDoor;
import dungeonmania.factory.FactoryHelpers;
import dungeonmania.factory.LogicFactory;

public class SwitchDoorBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        SwitchDoor switchdoor = new SwitchDoor(FactoryHelpers.extractKey(staticObject));
        switchdoor.setLogicRules(LogicFactory.getLogicType(FactoryHelpers.extractLogic(staticObject)));
        switchdoor.setPosition(FactoryHelpers.extractPosition(staticObject));
        switchdoor.setType(FactoryHelpers.extractType(staticObject));
        switchdoor.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(switchdoor.getUniqueId(), switchdoor);
    }
}
