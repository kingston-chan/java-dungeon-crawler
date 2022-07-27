package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.wire.Wire;
import dungeonmania.factory.FactoryHelpers;

public class WireBuilder implements StaticObjectBuilder{

    @Override
    public void buildStaticObject(JSONObject staticObject){
        Wire wire = new Wire();
        wire.setPosition(FactoryHelpers.extractPosition(staticObject));
        wire.setUniqueId(UUID.randomUUID().toString());
        wire.setType(FactoryHelpers.extractType(staticObject));
        DungeonManiaController.getDungeon().addDungeonObject(wire.getUniqueId(), wire);
    }
    
}
