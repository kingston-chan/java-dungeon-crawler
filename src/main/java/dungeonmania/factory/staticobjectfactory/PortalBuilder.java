package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.util.Position;

public class PortalBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(Position position, String type, String portalColour, int key) {
        Portal portal = new Portal();
        portal.setPosition(position);
        portal.setType(type);
        portal.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(portal.getUniqueId(), portal);
    }

}
