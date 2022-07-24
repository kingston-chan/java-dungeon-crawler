package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.factory.FactoryHelpers;

public class PortalBuilder implements StaticObjectBuilder {
    @Override
    public void buildStaticObject(JSONObject staticObject) {
        Portal portal = new Portal(FactoryHelpers.extractPortalColour(staticObject));
        portal.setPosition(FactoryHelpers.extractPosition(staticObject));
        portal.setType(FactoryHelpers.extractType(staticObject));
        portal.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(portal.getUniqueId(), portal);
    }

}
