package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.logicentities.Lightbulb;
import dungeonmania.factory.FactoryHelpers;
import dungeonmania.factory.LogicFactory;

public class LightbulbBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(JSONObject staticObject) {
        Lightbulb lightbulb = new Lightbulb(LogicFactory.getLogicType(FactoryHelpers.extractLogic(staticObject)));
        lightbulb.setPosition(FactoryHelpers.extractPosition(staticObject));
        lightbulb.setUniqueId(UUID.randomUUID().toString());
        lightbulb.setType(FactoryHelpers.extractType(staticObject));
        DungeonManiaController.getDungeon().addDungeonObject(lightbulb.getUniqueId(), lightbulb);
    }

}
