package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.exit.Exit;
import dungeonmania.factory.FactoryHelpers;

public class ExitBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(JSONObject staticObject) {
        Exit exit = new Exit();
        exit.setPosition(FactoryHelpers.extractPosition(staticObject));
        exit.setUniqueId(UUID.randomUUID().toString());
        exit.setType(FactoryHelpers.extractType(staticObject));
        DungeonManiaController.getDungeon().addDungeonObject(exit.getUniqueId(), exit);
    }

}
