package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.util.Position;

public class BoulderBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(Position position, String type, String portalColour, int key) {
        Boulder boulder = new Boulder();
        boulder.setPosition(position);
        boulder.setUniqueId(UUID.randomUUID().toString());
        boulder.setType(type);
        DungeonManiaController.getDungeon().addDungeonObject(boulder.getUniqueId(), boulder);
    }

}
