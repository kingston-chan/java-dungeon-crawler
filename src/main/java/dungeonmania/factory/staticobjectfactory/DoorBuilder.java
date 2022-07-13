package dungeonmania.factory.staticobjectfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.util.Position;

public class DoorBuilder implements StaticObjectBuilder {

    @Override
    public void buildStaticObject(Position position, String type, String portalColour, int key) {
        // TODO Auto-generated method stub
        Door door = new Door(key);
        door.setPosition(position);
        door.setUniqueId(UUID.randomUUID().toString());
        door.setType(type);
        DungeonManiaController.getDungeon().addDungeonObject(door.getUniqueId(), door);
    }

}
