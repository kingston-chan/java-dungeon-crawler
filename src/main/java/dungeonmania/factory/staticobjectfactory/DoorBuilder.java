package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.factory.FactoryHelpers;

public class DoorBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        Door door = new Door(FactoryHelpers.extractKey(staticObject));
        door.setPosition(FactoryHelpers.extractPosition(staticObject));
        door.setUniqueId(UUID.randomUUID().toString());
        door.setType(FactoryHelpers.extractType(staticObject));
        DungeonManiaController.getDungeon().addDungeonObject(door.getUniqueId(), door);
    }

}
