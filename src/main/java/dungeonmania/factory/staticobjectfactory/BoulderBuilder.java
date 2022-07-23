package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.factory.FactoryHelpers;

public class BoulderBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(JSONObject staticObject) {
        Boulder boulder = new Boulder();
        boulder.setPosition(FactoryHelpers.extractPosition(staticObject));
        boulder.setUniqueId(UUID.randomUUID().toString());
        boulder.setType(FactoryHelpers.extractType(staticObject));
        DungeonManiaController.getDungeon().addDungeonObject(boulder.getUniqueId(), boulder);
    }
}
